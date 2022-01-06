package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@Table(name = "orders") // 테이블 이름 설정 -> 안하면 order가 되는데 sql이랑 헷갈려서 뒤에 s를 붙임
@Getter @Setter
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch =  LAZY) // 자신이 n 상대가 1
    @JoinColumn(name = "member_id") // reference 하는 속성
    private Member member;

    @OneToMany(mappedBy = "order") // OrderItem에 있는 order 필드에 의해서 레퍼런스 됨
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = LAZY) // 1:1에서는 연관관계의 주인은 아무곳이나 해도 되지만 많이 사용하는 부분을 주인으로 설정
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate; // 주문시간

    @Enumerated(EnumType.STRING) // 열거
    private OrderStatus status; // 주문상태 [ORDER, CANCLE]

    //==연관관계 메서드==//
    public void setMember(Member member){
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery){
        this.delivery = delivery;
        delivery.setOrder(this);
    }
}
