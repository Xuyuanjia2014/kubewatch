package cn.abcsys.devops.v3.handler.deployment;

import cn.abcsys.devops.v2.deployer.db.dao.ApplicationEventsMapper;
import cn.abcsys.devops.v2.deployer.db.dao.V2ImageGroupMapper;
import cn.abcsys.devops.v2.deployer.db.dao.V2VersionMapper;
import cn.abcsys.devops.v2.deployer.db.model.V2ImageGroup;
import cn.abcsys.devops.v2.deployer.db.model.V2Version;
import cn.abcsys.devops.v3.handler.utils.ApplicationHandler;
import cn.abcsys.devops.v3.handler.utils.ConfigurationHandler;
import watch.SpringContextHelper;
import cn.abcsys.devops.v3.initialization.AllInit;
import cn.abcsys.devops.v3.initialization.HandlerInit;
import com.github.isdream.cwatcher.Handler;
import com.github.isdream.cwatcher.listeners.KubernetesListener;
import io.fabric8.kubernetes.api.model.apps.Deployment;
import io.fabric8.kubernetes.client.KubernetesClient;
import org.apache.log4j.Logger;

import java.util.Properties;

public class DeploymentConfigurationHandler extends Handler {

    private static Logger logger = Logger.getLogger(DeploymentConfigurationHandler.class);
    private V2ImageGroupMapper v2ImageGroupMapper = (V2ImageGroupMapper) SpringContextHelper.getBean("v2ImageGroupMapper");
    private ApplicationEventsMapper applicationEventsMapper = (ApplicationEventsMapper) SpringContextHelper.getBean("applicationEventsMapper");
    private V2VersionMapper v2VersionMapper = (V2VersionMapper) SpringContextHelper.getBean("v2VersionMapper");

    @Override
    public void doHandle(Properties props) {
        HandlerInit contextData = AllInit.contextMap.get(ApplicationHandler.getClientURL(props));
        props.put("contextData", contextData);
        Object obj = props.get("resource");
        Deployment deployment = (Deployment) obj;
        ConfigurationHandler configurationHandler = new ConfigurationHandler();
        configurationHandler.doHandle(deployment.getMetadata(), deployment.getApiVersion(), props);
        checkGreyReleaseOrVersionChange(props, deployment, (KubernetesClient) props.get(KubernetesListener.CLIENT), configurationHandler);
    }

    public void checkGreyReleaseOrVersionChange(Properties props, Deployment deployment, KubernetesClient client, ConfigurationHandler configurationHandler) {
        if (props.get("igId") == null) {
            return;
        }
        V2ImageGroup ig = v2ImageGroupMapper.selectByPrimaryKey((Integer) props.get("igId"));
        if (ig == null || ig.getImageGroupStrategy() == null ||
                ig.getOldReplica() == null ||
                ig.getTargetReplica() == null || ig.getShrinkageImageGroupId() == null) {
            return;
        }

        V2ImageGroup shrinkageIg = v2ImageGroupMapper.selectByPrimaryKey(ig.getShrinkageImageGroupId());
        if (shrinkageIg == null) {
            return;
        }
        logger.info("image group id:" + ig.getId() + " needs to do some grey things \n" + " tpye:" + ig.getImageGroupStrategy() + " oldImageGroupReplica:" + ig.getOldReplica() + " new version final replia:" + ig.getTargetReplica() + " old image group id:" + ig.getShrinkageImageGroupId());
        Deployment oldDeployment = client.extensions().deployments().inNamespace(deployment.getMetadata().getNamespace()).withName(shrinkageIg.getRealName()).get();
        if (oldDeployment != null &&
                deployment.getStatus().getReadyReplicas() != null
                && deployment.getStatus().getReadyReplicas().intValue() == deployment.getSpec().getReplicas().intValue()
                && ig.getTargetReplica() >= deployment.getSpec().getReplicas()
                && oldDeployment.getSpec().getReplicas() - 1 >= 0) {
            logger.info(deployment.getMetadata().getName() + " 's ready replica(current replica):" + deployment.getStatus().getReadyReplicas());
            logger.info(oldDeployment.getMetadata().getName() + " 's current replica:" + oldDeployment.getSpec().getReplicas());
            V2Version targetVersion = v2VersionMapper.selectByPrimaryKey(ig.getVersionId());
            V2Version shrinkVersion = v2VersionMapper.selectByPrimaryKey(shrinkageIg.getVersionId());

            if (ig.getImageGroupStrategy().equals("rolling-release")) {
                Integer temp1 = ig.getTargetReplica() - deployment.getStatus().getReadyReplicas();
                client.extensions().deployments().inNamespace(deployment.getMetadata().getNamespace()).withName(shrinkageIg.getRealName()).edit().
                        editSpec().
                        withReplicas(temp1).
                        endSpec().done();

            } else if (ig.getImageGroupStrategy().equals("grey-release")) {
                Integer temp2 = ig.getOldReplica() - deployment.getStatus().getReadyReplicas();
                client.extensions().deployments().inNamespace(deployment.getMetadata().getNamespace()).withName(shrinkageIg.getRealName()).edit().
                        editSpec().
                        withReplicas(temp2).
                        endSpec().done();
                configurationHandler.addEventInEnglish(Integer.valueOf(props.get("applicationId").toString()),
                        "GreyApplication",
                        "应用" + props.get("applicationName") + "的版本"
                                + targetVersion.getVersionName() + "灰度替换了版本"
                                + shrinkVersion.getVersionName(),
                        "watch.Application " + props.get("applicationName") + "'s version "
                                + targetVersion.getVersionName() + " grey replace version "
                                + shrinkVersion.getVersionName(),
                        Integer.valueOf(props.get("envId").toString()));
            }
            if (ig.getTargetReplica() > deployment.getSpec().getReplicas()) {
                Integer temp3 = deployment.getSpec().getReplicas() + 1;
                client.extensions().deployments().inNamespace(deployment.getMetadata().getNamespace()).withName(ig.getRealName()).edit().
                        editSpec().
                        withReplicas(temp3).
                        endSpec().done();
            } else if (ig.getTargetReplica().equals(deployment.getSpec().getReplicas())) {
                ig.setImageGroupStrategy(null);
                ig.setShrinkageImageGroupId(null);
                ig.setTargetReplica(null);
                ig.setOldReplica(null);

                v2ImageGroupMapper.updateByPrimaryKeySelective(ig);
            }
        }
    }
}
