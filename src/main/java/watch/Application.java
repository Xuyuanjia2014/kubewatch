package watch;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileNotFoundException;

@SpringBootApplication
@MapperScan({"cn.abcsys.devops.deployer.dao","cn.abcsys.devops.v2.deployer.db.dao"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

        try {
            (new SimpleWatchAddition()).startNsWatcher();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
