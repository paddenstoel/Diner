public class Order {
    private Dish[] dishes;
    private int size;

    public Order() {
        this.dishes = new Dish[16];
    }

    public Order(int size) {
        this.dishes = new Dish[size];
    }

    public Order(Dish[] dishes) {
        this.dishes = dishes;
        this.size = countNotNullElements(dishes);
    }

    public Boolean addDishToOrder(Dish dish) {
        if (size == dishes.length) {
            Dish[] doubleDishes = new Dish[dishes.length * 2];
            System.arraycopy(dishes, 0, doubleDishes, 0, dishes.length);
            dishes = doubleDishes;
            dishes[size] = dish;
            size++;
        } else {
            for (Dish dish1 : dishes) {
                if (dish1 == null) {
                    dish1 = dish;
                    size++;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean removeDishFromOrder(String name) {
        for (int i = 0; i < dishes.length; i++) {
            if (dishes[i].getName().equals(name)) {
                shiftToLeft(dishes, i);
                size--;
                return true;
            }
        }
        return false;
    }

    public int removeAllDishesFromOrder(String name) {
        int deletedDishes = 0;
        for (int i = 0; i < dishes.length; i++) {
            if (dishes[i].getName().equals(name)) {
                shiftToLeft(dishes, i);
                size--;
                deletedDishes++;
            }
        }
        return deletedDishes;
    }

    public int getTotalDishesCount() {
        return this.size;
    }

    public Dish[] getDishes() {
        Dish[] result = new Dish[this.size];
        System.arraycopy(dishes, 0, result, 0, this.size);
        return result;
    }

    public double getTotalPrice() {
        double totalPrice = 0;
        for (Dish dish : dishes) {
            totalPrice += dish.getPrice();
        }
        return totalPrice;
    }

    public int getOrderedDishesCount(String name) {
        int orderedDishesCount = 0;
        for (Dish dish : dishes) {
            if (dish.getName().equals(name)) {
                orderedDishesCount++;
            }
        }
        return orderedDishesCount;
    }

    public String[] getOrderedDishesNames() {
        String[] buffer = new String[this.size];
        int countDishNameDistinct = 0;

        for (int i = 0; i < this.dishes.length; i++) {
            if (!isExistingInArray(buffer, dishes[i].getName())) {
                buffer[countDishNameDistinct] = dishes[i].getName();
                countDishNameDistinct++;
            }
        }
        String[] finalResult = new String[countDishNameDistinct];
        System.arraycopy(buffer, 0, finalResult, 0, countDishNameDistinct);
        return finalResult;

    }

    public Dish[] getOrderedDishesPriceDesc() {
        Dish[] result = new Dish[this.size];
        System.arraycopy(dishes, 0, result, 0, this.dishes.length);
        sortArrayAlgorithm(result);

        return result;
    }


    private int countNotNullElements(Dish[] dishes) {
        int count = 0;
        for (Dish dish1 : dishes) {
            if (dish1 != null) {
                count++;
            }
        }
        return count;
    }

    private void shiftToLeft(Dish[] dishes, int index) {
        System.arraycopy(dishes, index + 1, dishes, index, size - index + 1);
        dishes[size - 1] = null;
    }

    private boolean isExistingInArray(String[] array, String name) {
        for (int i = 0; i < array.length; i++) {
            if (name.equals(array[i])) {
                return true;
            }
        }
        return false;
    }

    private Dish[] sortArrayAlgorithm(Dish[] array) { //sort in descending order
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (array[i].getPrice() >= array[j].getPrice()) {
                    Dish x = array[i];
                    array[i] = array[j];
                    array[j] = x;
                }
            }
        }
        return array;
    }
}
