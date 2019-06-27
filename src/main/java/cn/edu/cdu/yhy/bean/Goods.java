package cn.edu.cdu.yhy.bean;

/**
 * @Author: 羊泓运
 * @Date: 2019/5/8 22:44
 * @Version 1.0
 */
public class Goods {
    private Integer id;

    private String name;

    private String introduce;

    private Integer type;

    private Integer price;

    private Integer stock;

    public Goods(Integer id, String name, String introduce, Integer type, Integer price, Integer stock) {
        this.id = id;
        this.name = name;
        this.introduce = introduce;
        this.type = type;
        this.price = price;
        this.stock = stock;
    }

    public Goods(String name, String introduce, Integer type, Integer price, Integer stock) {
        this.name = name;
        this.introduce = introduce;
        this.type = type;
        this.price = price;
        this.stock = stock;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
