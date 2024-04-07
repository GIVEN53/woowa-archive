package christmas.domain.order;

import christmas.domain.menu.Menu;

public class Order {
    private final Menu menu;
    private final OrderQuantity orderQuantity;

    private Order(Menu menu, OrderQuantity orderQuantity) {
        this.menu = menu;
        this.orderQuantity = orderQuantity;
    }

    public static Order of(String name, int quantity) {
        Menu menu = Menu.findByName(name);
        OrderQuantity orderQuantity = new OrderQuantity(quantity);
        return new Order(menu, orderQuantity);
    }

    String getMenuName() {
        return menu.getName();
    }

    int getMenuPrice() {
        return menu.getPrice();
    }

    int getOrderQuantity() {
        return orderQuantity.quantity();
    }

    boolean hasBeverageMenu() {
        return menu.isBeverage();
    }

    boolean hasDessertMenu() {
        return menu.isDessert();
    }

    boolean hasMainMenu() {
        return menu.isMain();
    }
}
