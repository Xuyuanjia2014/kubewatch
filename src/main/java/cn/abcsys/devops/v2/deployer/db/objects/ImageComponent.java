package cn.abcsys.devops.v2.deployer.db.objects;

import cn.abcsys.devops.v2.deployer.db.model.*;

import java.util.List;

/**
 * Created by Administrator on 2017/9/17.
 */
public class ImageComponent {
    private V2Image image;
    private List<V2Ports> ports;
    private V2Resources resources;
    private List<V2Envs> envs;
    private List<V2Args> args;
    private List<V2VolumeMounts> volumeMounts;
    private List<V2Probe> probes;

    public V2Image getImage() {
        return image;
    }

    public void setImage(V2Image image) {
        this.image = image;
    }

    public List<V2Ports> getPorts() {
        return ports;
    }

    public void setPorts(List<V2Ports> ports) {
        this.ports = ports;
    }

    public V2Resources getResources() {
        return resources;
    }

    public void setResources(V2Resources resources) {
        this.resources = resources;
    }

    public List<V2Envs> getEnvs() {
        return envs;
    }

    public void setEnvs(List<V2Envs> envs) {
        this.envs = envs;
    }

    public List<V2Args> getArgs() {
        return args;
    }

    public void setArgs(List<V2Args> args) {
        this.args = args;
    }

    public List<V2Probe> getProbes() {
        return probes;
    }

    public void setProbes(List<V2Probe> probes) {
        this.probes = probes;
    }

    public List<V2VolumeMounts> getVolumeMounts() {
        return volumeMounts;
    }

    public void setVolumeMounts(List<V2VolumeMounts> volumeMounts) {
        this.volumeMounts = volumeMounts;
    }
}
