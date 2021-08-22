import java.util.ArrayList;

public class TodoList {
    ArrayList <String> todoList = new ArrayList<>();

    public void add(String todo) {
        this.todoList.add(todo);
        System.out.println("Задача " + "\"" + todo + "\"" + " добавлена под номером " + this.todoList.indexOf(todo));
    }

    public void add(int index, String todo) {
        if (index > this.todoList.size()) {
            index = this.todoList.size();
            System.out.println("Заданный индекс превышает размер ArrayList, данная задача будет находиться в конце списка");}
            this.todoList.add(index, todo);
        }

    public void edit(String todo, int index) {
        if (index > this.todoList.size()) {
            System.out.println("Задачи с таким индексом не существует :( \nВзгляните на список задач:");
            getTodos();
        } else
            this.todoList.set(index, todo);
    }

    public void delete(int index) {
        if (index >= this.todoList.size()) {
            System.out.println("Задачи с таким индексом не существует :( \nВзгляните на список задач:");
            getTodos();
        } else
            this.todoList.remove(index);
    }

    public ArrayList<String> getTodos() {
        for (int i = 0; i < todoList.size(); i++){
        System.out.println(todoList.indexOf(todoList.get(i)) + " - " + todoList.get(i));}
        return todoList;
    }
}