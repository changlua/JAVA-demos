package xyz.changlu.builder.demo3;

/**
 * @ClassName Product
 * @Author ChangLu
 * @Date 2021/3/17 16:41
 * @Description TODO
 */
public class Product {
    private int id;
    private String name;
    private int age;

    public Product(Builder builder){
        this.id = builder.id;
        this.name = builder.name;
        this.age = builder.age;
    }

    public static class Builder{
        private int id;
        private String name;
        private int age;

        public Builder(int id){
            this.id = id;
        }

        public Builder setName(int id){
            this.id = id;
            return this;
        }

        public Builder setId(String name){
            this.name = name;
            return this;
        }

        public Builder setAge(int age){
            this.age = age;
            return this;
        }

        public Product builder(){
            return new Product(this);
        }
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

//测试类
class Test{
    public static void main(String[] args) {
        Product builder = new Product.Builder(15).setAge(100)
                .builder();
        System.out.println(builder);
    }
}
