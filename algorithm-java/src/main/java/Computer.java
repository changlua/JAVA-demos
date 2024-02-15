public class Computer {
  private String name;
  private String GPU;
  private String CPU;
  private String power;
  private String appearance;

  public Computer(String name,String CPU,String GPU,String power,String appearance){
    this.name=name;
    this.GPU=GPU;
    this.CPU=CPU;
    this.power=power;
    this.appearance=appearance;
  }

  public String getName(){
    return name;
  }

  public void setName(String name){
    this.name=name;
  }

  public String getCPU(){
    return CPU;
  }

  public void setCPU(String CPU){
    this.CPU=CPU;
  }

  public String getGPU(){
    return GPU;
  }

  public void setGPU(String GPU){
    this.GPU=CPU;
  }

  public String getPower(){
    return power;
  }

  public void setPower(String power){
    this.power=power;
  }

  public String getAppearance(){
    return appearance;
  }

  public void setAppearance(String appearance){
    this.appearance=appearance;
  }

  public void printConfiguration(){
    System.out.printf("Name "+getName());
    System.out.printf("Cpu "+getCPU());
    System.out.printf("Gpu "+getGPU());
    System.out.printf("Power "+getPower());
    System.out.printf("Appearance "+getAppearance());
  }
}
