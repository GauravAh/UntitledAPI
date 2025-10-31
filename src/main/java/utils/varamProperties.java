package utils;

import config.Constant;

import java.io.InputStream;
import java.util.Properties;

public class varamProperties {

    static Properties allprop = new Properties();
    static Properties envprop = new Properties();
    static Properties endpointprop = new Properties();

    static {
        String basePropfile = Constant.Base_Prop_File;
        String envPath = Constant.Env_Path;
        String devPath = Constant.Default_env;
        String endpointPath = Constant.API_Endpoint_File;

        try{
            InputStream baseStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(basePropfile);
            allprop.load(baseStream);
        }catch (Exception e){
            e.printStackTrace();
        }

        String activeEnv = allprop.getProperty("env", devPath);  // use `env` key from base.properties if present

        try {
            envprop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(envPath + "/" + activeEnv +
                    "/" + "system.properties"));
        }catch (Exception e){
            e.printStackTrace();
        }

        try{
            endpointprop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(endpointPath));
        }catch (Exception e){
            e.printStackTrace();
        }

        allprop.putAll(envprop);
        allprop.putAll(endpointprop);
    }

    public static String get(String key) {
        return allprop.getProperty(key);
    }

}
