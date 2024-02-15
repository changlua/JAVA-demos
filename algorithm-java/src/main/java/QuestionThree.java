public class QuestionThree {

  public static void main(String[] args) {
    Computer Computer_01=new Computer("Ausu","i9 13900K","RTX 4090","2000W","Brand New");
    Computer Computer_02=new Computer("null","E5 2650V2","RX 580","250W","Very Old");
    Computer_01.printConfiguration();
    Laptop Laptop_01=new Laptop("Dell","i7 13700k","RTX 4070Ti","400W","Normal");
    Laptop_01.printConfiguration();

  }

}
