package cn.abcsys.devops.v2.deployer.db.objects;

import cn.abcsys.devops.v2.deployer.db.model.V2ImageGroup;
import cn.abcsys.devops.v2.deployer.db.model.V2Labels;
import cn.abcsys.devops.v2.deployer.db.model.V2Volumes;
import io.fabric8.kubernetes.api.model.Affinity;

import java.util.List;

/**
 * Created by Administrator on 2017/9/17.
 */
public class DeploymentComponent implements IdeployType{
    private V2ImageGroup imageGroup;
    private List<ImageComponent> images;
    private List<V2Labels> labels;
    private List<V2Volumes> volumes;
    private String deployType;
    private Affinity affinity;

    @Override
    public String getDeployType() {
        return deployType;
    }
    @Override
    public void setDeployType(String deployType) {
        this.deployType = deployType;
    }

    public V2ImageGroup getImageGroup() {
        return imageGroup;
    }

    public void setImageGroup(V2ImageGroup imageGroup) {
        this.imageGroup = imageGroup;
    }

    public List<ImageComponent> getImages() {
        return images;
    }

    public void setImages(List<ImageComponent> images) {
        this.images = images;
    }

    public List<V2Labels> getLabels() {
        return labels;
    }

    public void setLabels(List<V2Labels> labels) {
        this.labels = labels;
    }

    public List<V2Volumes> getVolumes() {
        return volumes;
    }

    public void setVolumes(List<V2Volumes> volumes) {
        this.volumes = volumes;
    }

    public Affinity getAffinity() {
        return affinity;
    }

    public void setAffinity(Affinity affinity) {
        this.affinity = affinity;
    }
}
