public class Laptop extends Computer {
    private double weight;
    private String screen;
    private String usableDuration;

    public Laptop(String name, String CPU, String GPU, String appearance, String power, double weight, String screen, String usableDuration) {
        super(name, CPU, GPU, appearance, power);
        this.weight = weight;
        this.screen = screen;
        this.usableDuration = usableDuration;
    }

    public Laptop(String name, String CPU, String GPU, String power, String appearance) {
      super(name, CPU, GPU, appearance, power);
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public String getUsableDuration() {
        return usableDuration;
    }

    public void setUsableDuration(String usableDuration) {
        this.usableDuration = usableDuration;
    }

    public void printLoptopConfiguration() {
        System.out.printf("Name " + getName());
        System.out.printf("Cpu " + getCPU());
        System.out.printf("Gpu " + getGPU());
        System.out.printf("Weight" + getWeight());
        System.out.printf("Screen" + getScreen());
        System.out.printf("usableDuration" + getUsableDuration());
    }
}
