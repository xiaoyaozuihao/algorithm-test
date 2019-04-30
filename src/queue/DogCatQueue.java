import java.util.LinkedList;
import java.util.Queue;

/**
 * @author xuyh
 * @date 2019/4/23
 */
public class DogCatQueue {

    public static class Pet{
        private String type;

        public Pet(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }
    public static class Dog extends Pet{
        public Dog() {
            super("dog");
        }
    }
    public static class Cat extends Pet{
        public Cat(){
            super("cat");
        }
    }
    public static class PetEnterQueue{
        private Pet pet;
        private long count;

        public PetEnterQueue(Pet pet, long count){
            this.pet=pet;
            this.count=count;
        }
        public Pet getPet(){
            return pet;
        }
        public long getCount(){
            return count;
        }
        public String getPetEnterType(){
            return pet.getType();
        }
    }

    public static class DogCatEnterQueue{
        private Queue<PetEnterQueue> dogQueue;
        private Queue<PetEnterQueue> catQueue;
        private long count;
        public DogCatEnterQueue(){
            dogQueue=new LinkedList<>();
            catQueue=new LinkedList<>();
            count=0;
        }


        public void add(Pet pet){
            if(pet.getType().equals("cat")){
                catQueue.add(new PetEnterQueue(pet,count++));
            }else if(pet.getType().equals("dog")){
                dogQueue.add(new PetEnterQueue(pet,count++));
            }else{
                throw new RuntimeException("no this pet");
            }
        }

        public void pollAll(){
            if(!isDogQueueEmpty()&&!isCatQueueEmpty()){
                if(dogQueue.peek().getCount()<catQueue.peek().getCount()){
                    PetEnterQueue poll = dogQueue.poll();
                    System.out.println(poll.getPet().getType()+":"+poll.getCount());
                }else{
                    PetEnterQueue poll = catQueue.poll();
                    System.out.println(poll.getPet().getType()+":"+poll.getCount());
                }
            }else if(!isDogQueueEmpty()){
                PetEnterQueue poll = dogQueue.poll();
                System.out.println(poll.getPet().getType()+":"+poll.getCount());
            }else if(!isCatQueueEmpty()){
                PetEnterQueue poll = catQueue.poll();
                System.out.println(poll.getPet().getType()+":"+poll.getCount());
            }else{
                throw new RuntimeException("neither cat nor dog");
            }
        }

        public PetEnterQueue pollDog(){
            if(isDogQueueEmpty()){
                throw new RuntimeException("dogQueue is empty");
            }
            PetEnterQueue poll = dogQueue.poll();
            System.out.println(poll.getPet().getType()+":"+poll.getCount());
            return poll;
        }
        public Cat pollCat(){
            if(isCatQueueEmpty()){
                throw new RuntimeException("catQueue is empty");
            }
            return (Cat) catQueue.poll().getPet();
        }
        public boolean isEmpty(){
            return dogQueue.isEmpty()&&catQueue.isEmpty();
        }

        public boolean isDogQueueEmpty(){
            return dogQueue.isEmpty();
        }
        public boolean isCatQueueEmpty(){
            return catQueue.isEmpty();
        }
    }

    public static void main(String[] args) {
        DogCatEnterQueue test = new DogCatEnterQueue();

        Pet dog1 = new Dog();
        Pet cat1 = new Cat();
        Pet dog2 = new Dog();
        Pet cat2 = new Cat();
        Pet dog3 = new Dog();
        Pet cat3 = new Cat();

        test.add(dog1);
        test.add(cat1);
        test.add(dog2);
        test.add(cat2);
        test.add(dog3);
        test.add(cat3);

        test.add(dog1);
        test.add(cat1);
        test.add(dog2);
        test.add(cat2);
        test.add(dog3);
        test.add(cat3);

        test.add(dog1);
        test.add(cat1);
        test.add(dog2);
        test.add(cat2);
        test.add(dog3);
        test.add(cat3);
        while (!test.isDogQueueEmpty()) {
            test.pollDog();
        }
        while (!test.isEmpty()) {
            test.pollAll();
        }
    }
}
