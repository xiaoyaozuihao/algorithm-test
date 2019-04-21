import java.util.Comparator;
import java.util.TreeSet;

/**
 * @author xuyh
 * @date 2019/4/11
 */
public class CustomComparator {
    public static class Student{
        private String name;
        private int age;
        private int id;

        public Student(String name, int age, int id) {
            this.name = name;
            this.age = age;
            this.id = id;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", id=" + id +
                    '}';
        }
    }

    public static class IdAsendingComparator implements Comparator<Student> {
        @Override
        public int compare(Student o1, Student o2) {
            return o2.getId()-o1.getId();
        }
    }

    public static void main(String[] args) {
        //优先级队列，就是堆的应用
//        PriorityQueue<Student> priorityQueue=new PriorityQueue<>(new IdAsendingComparator());
//        priorityQueue.add(new Student("haha",12,1));
//        priorityQueue.add(new Student("hehe",14,2));
//        priorityQueue.add(new Student("hiahia",16,3));
//        while(!priorityQueue.isEmpty()){
//            System.out.println(priorityQueue.poll());
//        }
        //数组排序
//        Student[] students=new Student[]{new Student("A",16,1),new Student("B",23,3),new Student("C",12,2)};
//        Arrays.sort(students,new IdAsendingComparator());
//        for (Student student : students) {
//            System.out.println(student);
//        }
        TreeSet<Student> set=new TreeSet<>(Comparator.comparing(Student::getId));
        set.add(new Student("sdfa",121,1));
        set.add(new Student("sdfasdf",1221,3));
        set.add(new Student("sdfaaa",123,2));
        while(!set.isEmpty()){
            System.out.println(set.pollFirst());
        }
    }
}
