/**
 * Copyright (2018, ) Institute of Software, Chinese academy of Sciences
 */
package com.github.isdream.cwatcher.listeners;

import com.github.isdream.cdispatcher.ModelParamtersGenerator;
import com.github.isdream.cdispatcher.generators.KubernetesModelParametersGenerator;
import com.github.isdream.cdispatcher.utils.ObjectUtils;
import com.github.isdream.cwatcher.Configure;
import com.github.isdream.cwatcher.Handler;
import com.github.isdream.cwatcher.Listener;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientException;
import io.fabric8.kubernetes.client.Watcher;
import io.fabric8.kubernetes.client.dsl.AnyNamespaceable;
import io.fabric8.kubernetes.client.dsl.Namespaceable;
import io.fabric8.kubernetes.client.dsl.Watchable;
import io.fabric8.openshift.client.OpenShiftClient;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Properties;

/**
 * @author wuheng@otcaix.iscas.ac.cn
 */
public class KubernetesListener extends Listener {

    public final static String PROPERTY_KIND = "kind";

    public final static String PROPERTY_NAMESPACE = "namespace";

    public final static String PROPERTY_FILTERS = "filters";

    public final static String PROPERTY_ACTION = "action";

    public final static String PROPERTY_RESOURCE = "resource";

    public final static String ALL_NAMESPACE = "all-namespaces";

    public final static String PROPERTY_CLIENT_TYPE = "property-client-type";

    public final static String CLIENT = "ip";

    public KubernetesWatcher getSelfWatch() {
        return selfWatch;
    }

    public void setSelfWatch(KubernetesWatcher selfWatch) {
        this.selfWatch = selfWatch;
    }

    private KubernetesWatcher selfWatch;
    private KubernetesClient client;

    private static Logger logger = Logger.getLogger(KubernetesListener.class);

    public KubernetesClient getClient() {
        return client;
    }

    public void setClient(KubernetesClient client) {
        this.client = client;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void start(Object client, Configure config) throws Exception {
//		System.out.println(config.getKind());
//		System.out.println(config.getHandlers());
//		System.out.println(config.getProperties());
//		System.out.println(config.getApiVersion());
        if (ObjectUtils.isNull(client) && ObjectUtils.isNull(config)) {
            throw new Exception("Invalid paremeters");
        }

        // it may throw Exception if wrong PROPERTY_KIND values
        Object model = getModelParamtersGenerator()
                .getKindModel(client, config.getKind());
        if (model instanceof Namespaceable || model instanceof AnyNamespaceable) {
            String namespace = config.getProperties().
                    getOrDefault(PROPERTY_NAMESPACE, ALL_NAMESPACE);
            if (ALL_NAMESPACE.equals(namespace)) {
                model = ((AnyNamespaceable) model).inAnyNamespace();
            } else {
                model = ((Namespaceable) model).inNamespace(namespace);
            }
        }
        List<String> handlers = config.getHandlers();

        if (handlers.isEmpty()) {
            throw new Exception("No handlers");
        }
//		System.out.println(handlers.get(0));
        Handler handler = (Handler) Class.forName(handlers.remove(0)).newInstance();
        Handler thisHandler = handler;
//		System.out.println(model.getClass());
        for (String name : handlers) {
            //System.out.println(name);
            Handler nextHandler = (Handler) Class.forName(name).newInstance();
            thisHandler.setNextHandler(nextHandler);
            thisHandler = nextHandler;
        }
        this.selfWatch = new KubernetesWatcher(handler, client);
        ((Watchable) model).watch(this.selfWatch);
    }

    @Override
    public void stop() {
        StringBuffer sb = new StringBuffer("Remove KubernetesListener:");
        if(this.selfWatch.k8s != null){
            sb.append(this.selfWatch.k8s.getMasterUrl());
        }
        else {
            sb.append(this.selfWatch.ocp.getMasterUrl());
        }
        sb.append(" due to bad connection!");
        logger.info(sb);
        this.selfWatch = null;
    }

    public ModelParamtersGenerator getModelParamtersGenerator() {
        return new KubernetesModelParametersGenerator();
    }


    @SuppressWarnings("rawtypes")
    public class KubernetesWatcher implements Watcher {

        protected Handler handler;

        private OpenShiftClient ocp = null;
        private KubernetesClient k8s = null;

        public KubernetesWatcher(Handler handler, Object client) {
            super();
            if (client instanceof OpenShiftClient) {
                this.ocp = (OpenShiftClient) client;
            } else if (client instanceof KubernetesClient) {
                this.k8s = (DefaultKubernetesClient) client;
            }
            this.handler = handler;
        }

        @Override
        public void eventReceived(Action action, Object resource) {
            Properties props = new Properties();
            props.put(PROPERTY_ACTION, action.name());
            props.put(PROPERTY_RESOURCE, resource);
            if (this.ocp != null) {
                props.put(CLIENT, ocp);
                props.put(PROPERTY_CLIENT_TYPE, "openshift");
            } else {
                props.put(CLIENT, k8s);
                props.put(PROPERTY_CLIENT_TYPE, "kubernetes");
            }
            handler.handle(props);
        }

        @Override
        public void onClose(KubernetesClientException cause) {
            // ignore here
//            if (this.ocp != null) {
//                AllInit.removeWatch(this.ocp);
//            } else {
//                AllInit.removeWatch(this.k8s);
//            }
            System.out.println(" can it trigger onClose event?");
        }

    }
}
