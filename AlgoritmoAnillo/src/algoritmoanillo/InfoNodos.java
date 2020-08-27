
package algoritmoanillo;

public class InfoNodos {
    
    public String ip;
    public String puerto;
    public String id;

    public InfoNodos (String id, String puerto, String ip){
        this.ip     = ip;
        this.puerto = puerto;
        this.id     = id;
    }
    
    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setPuerto(String puerto) {
        this.puerto = puerto;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public String getPuerto() {
        return puerto;
    }

    public String getId() {
        return id;
    }
    

    
    
    
}
