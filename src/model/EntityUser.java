package model;

/**
 * 
 * @author HERNANDEZ Raul @devrulls
 */
public class EntityUser {
    private int id_user;
    private String name;
    private String username;
    private String pwd;
    private int id_role;
    private String role;
    private String description_role;

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getId_role() {
        return id_role;
    }

    public void setId_role(int id_role) {
        this.id_role = id_role;
    }
    
    public int getRole() {
        return id_role;
    }

    public void setRole(String role) {
            if ("SUPER ADMIN".equals(role)) {
                setId_role(1);
                
            } else if ("ADMIN".equals(role)) {
                setId_role(2);
            }
            
    }

    public String getDescription_role() {
        return description_role;
    }

    public void setDescription_role(String description_role) {
        this.description_role = description_role;
    }
    
}
