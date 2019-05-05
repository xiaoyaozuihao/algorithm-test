package queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author xuyh
 * @date 2019/4/25
 */
public class DogCatQueueTest {
    public static class Pet {
        private String petType;

        public Pet(String petType) {
            this.petType = petType;
        }

        public String getPetType() {
            return petType;
        }
    }

    public static class Cat extends Pet {
        public Cat() {
            super("cat");
        }
    }

    public static class Dog extends Pet {
        public Dog() {
            super("dog");
        }
    }

    public static class PetEnter {
        private Pet pet;
        private long count;

        public PetEnter(Pet pet, long count) {
            this.pet = pet;
            this.count = count;
        }

        public Pet getPet() {
            return pet;
        }

        public long getCount() {
            return count;
        }
    }

    public static class DogCatEnterQueue {
        private Queue<PetEnter> dogQueue;
        private Queue<PetEnter> catQueue;
        private long count;

        public DogCatEnterQueue() {
            dogQueue = new LinkedList<>();
            catQueue = new LinkedList<>();
            count=0;
        }

        public void add(Pet pet) {
            if (pet.getPetType().equals("cat")) {
                catQueue.add(new PetEnter(pet, count++));
            } else if (pet.getPetType().equals("dog")) {
                dogQueue.add(new PetEnter(pet, count++));
            } else {
                throw new RuntimeException("neither cat nor dog");
            }
        }

        public void pollAll() {
            if(!isDogQueueEmpty()&&!isCatQueueEmpty()){
                if(dogQueue.peek().getCount()<catQueue.peek().getCount()){
                    PetEnter poll = dogQueue.poll();
                    System.out.println(poll.getPet().getPetType()+":"+poll.getCount());
                }else{
                    PetEnter poll1 = catQueue.poll();
                    System.out.println(poll1.getPet().getPetType()+":"+ poll1.getCount());
                }
            }else if (!isCatQueueEmpty()){
                PetEnter poll1 = catQueue.poll();
                System.out.println(poll1.getPet().getPetType()+":"+ poll1.getCount());
            }else if(!isDogQueueEmpty()){
                PetEnter poll = dogQueue.poll();
                System.out.println(poll.getPet().getPetType()+":"+poll.getCount());
            }else {
                throw new RuntimeException("the queue is empty");
            }
        }

        public void pollCat(){
            if(isCatQueueEmpty()){
                throw new RuntimeException("catQueue is empty");
            }
            PetEnter poll = catQueue.poll();
            System.out.println(poll.getPet().getPetType()+":"+poll.getCount());
        }

        public void pollDog(){
            if(isDogQueueEmpty()){
                throw new RuntimeException("dogQueue is empty");
            }
            PetEnter poll = dogQueue.poll();
            System.out.println(poll.getPet().getPetType()+":"+poll.getCount());
        }

        public boolean isDogQueueEmpty(){
            return dogQueue.isEmpty();
        }
        public boolean isCatQueueEmpty(){
            return catQueue.isEmpty();
        }
        public boolean isEmpty(){
            return dogQueue.isEmpty()&&catQueue.isEmpty();
        }
    }

    public static void main(String[] args) {
        DogCatEnterQueue test=new DogCatEnterQueue();
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
//        while (!test.isDogQueueEmpty()) {
//            test.pollDog();
//        }
        while (!test.isEmpty()) {
            test.pollAll();
        }

    }
}
