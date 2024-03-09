package test;

public class MemberDTO { 
    //데이터에 클래스를 만들어 사용   
    private String id;
    private String pwd;
    private String name;
    private String tel;
    private String dong;
     private String hosu;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getPwd() {
        return pwd;
    }
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getTel() {
        return tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }
    public String getdong() {
        return dong;
    }
    public void setdong(String dong) {
        this.dong = dong;
    }
  
    public String gethosu() {
        return hosu;
    }
    public void sethosu(String hosu) {
        this.hosu = hosu;
    }

    @Override
    public String toString() {
        return "MemberDTO [id=" + id + ", pwd=" + pwd + ", name=" + name
                + ", tel=" + tel + ", dong=" + dong + ", hosu=" + hosu
                +  "]";
    }
}