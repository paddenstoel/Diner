public class OrderManager {
    private int tableQuantity;
    private Order[] orders;

    public OrderManager(int tableQuantity) {
        this.tableQuantity = tableQuantity;
        this.orders = new Order[tableQuantity];
    }

    public void addOrderToTable(int tableNumber, Order order) {
        if (orders[tableNumber] == null) {
            orders[tableNumber] = order;
        }
    }

    public Order getTableOrder(int tableNumber) {
        return orders[tableNumber];
    }

    public void addDishToOrder(int tableNumber, Dish dish) {
        orders[tableNumber].addDishToOrder(dish);
    }

    public void setFreeTable(int tableNumber) {
        orders[tableNumber] = null;
    }

    public int getFreeTableNumber() {
        for (int i = 0; i < orders.length; i++) {
            if (orders[i] == null) {
                return i;
            }
        }
        return -1;
    }

    public int[] getFreeTableNumbers() {
        int[] buffer = new int[orders.length];
        int freeTableNumbersCount = 0;

        for (int i = 0; i < orders.length; i++) {
            if (orders[i] == null) {
                buffer[freeTableNumbersCount] = i;
                freeTableNumbersCount++;
            }
        }

        int[] freeTableNumbers = new int[freeTableNumbersCount];
        System.arraycopy(buffer, 0, freeTableNumbers, 0, freeTableNumbersCount);
        return freeTableNumbers;
    }

    public int[] getReservedTableNumbers() {
        int[] buffer = new int[orders.length];
        int reservedTableNumbersCount = 0;

        for (int i = 0; i < orders.length; i++) {
            if (orders[i] != null) {
                buffer[reservedTableNumbersCount] = i;
                reservedTableNumbersCount++;
            }
        }

        int[] reservedTableNumbers = new int[reservedTableNumbersCount];
        System.arraycopy(buffer, 0, reservedTableNumbers, 0, reservedTableNumbersCount);

        return reservedTableNumbers;

    }

    public Order[] getActualOrders() {
        Order[] buffer = new Order[orders.length];
        int ordersCount = 0;
        for (int i = 0; i < orders.length; i++) {
            if (orders[i] != null) {
                buffer[i] = orders[i];
                ordersCount++;
            }
        }
        Order[] actualOrders = new Order[ordersCount];
        System.arraycopy(buffer, 0, actualOrders, 0, ordersCount);

        return actualOrders;
    }

    public double getTotalOrdersCost() {
        double totalOrdersCost = 0;
        Order[] actualOrders = getActualOrders();

        for (int i = 0; i < getActualOrders().length; i++) {
            totalOrdersCost += actualOrders[i].getTotalPrice();
        }
        return totalOrdersCost;
    }

    public int getDishQuantity(String dishName) {
        Order[] actualOrders = getActualOrders();
        int dishQuantity = 0;
        for (Order actualOrder : actualOrders) {
            Dish[] dishes = actualOrder.getDishes();
            for (Dish dish : dishes) {
                if (dish.getName().equals(dishName)) {
                    dishQuantity++;
                }
            }
        }
        return dishQuantity;
    }

}

