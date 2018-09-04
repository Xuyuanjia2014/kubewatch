package watch;

import com.github.isdream.cwatcher.Configure;
import com.github.isdream.cwatcher.Listener;
import com.github.isdream.cwatcher.Parser;
import com.github.isdream.cwatcher.listeners.KubernetesListener;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import org.apache.log4j.Logger;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleWatchAddition {

    public static Map<String, List<Listener>> listenersMap = new HashMap<>();

    private static Logger logger = Logger.getLogger(SimpleWatchAddition.class);

    public static KubernetesClient getKubernetesClient(String ip,String type,String port){
        if(type.equals("https")){
            return null;
        }
        String master = type+"://" + ip + ":" + port ;
        io.fabric8.kubernetes.client.Config config = new io.fabric8.kubernetes.client.ConfigBuilder().withMasterUrl(master).build();
        return new DefaultKubernetesClient(config);
    }

    public static Listener getKubernetesListener(File file,KubernetesClient namespaceClient) {
        Configure configure = null;
        try {
            if (file.getAbsolutePath().contains("deploymentConfig"))
                return null;
            configure = new Parser().parse(file.getAbsolutePath());
            Listener namespaceListener = new KubernetesListener();
            logger.info(configure.getKind());
            namespaceListener.setClient(namespaceClient);
            namespaceListener.start(namespaceClient, configure);
            return namespaceListener;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void startNsWatcher() throws FileNotFoundException {
        KubernetesClient ns = getKubernetesClient("啊啊啊啊","http","啊啊啊啊");
        List<Listener> lis = new ArrayList<>();
        Listener nsListener = getKubernetesListener(ResourceUtils.getFile( "classpath:namespace/namespaceWatcher.yaml"),ns );
        lis.add(nsListener);
        listenersMap.put( "啊啊啊啊,0", lis);
    }

}
