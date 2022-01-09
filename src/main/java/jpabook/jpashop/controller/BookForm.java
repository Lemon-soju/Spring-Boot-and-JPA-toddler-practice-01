package jpabook.jpashop.controller;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BookForm {

    private Long id; // Alt + Shift + 마우스 좌클릭 -> 여러줄 동시에 입력
    private String name; // Ctrl + Shift + 화살표 -> 여러줄 동시에 드래그
    private int price; // Shift + Tab -> 여러줄 왼쪽으로 이동
    private int stockQuantity;
    private String author;
    private String isbn;
}
