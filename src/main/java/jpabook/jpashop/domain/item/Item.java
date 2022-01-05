package jpabook.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // 한 테이블에 모든 속성을 넣어서 관리
@DiscriminatorColumn(name = "dtype") // 구별하기
@Getter @Setter
public abstract class Item { // 구현체를 가지고 할거라서 추상 클래스로 만듬
    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

}
