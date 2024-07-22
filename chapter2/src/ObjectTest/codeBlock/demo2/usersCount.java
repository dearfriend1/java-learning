package ObjectTest.codeBlock.demo2;

public class usersCount {
    public static void main(String[] args) {
        Users users;

        System.out.println(Users.count);

        users = new Users();
        System.out.println(Users.count);


        Users users1 = users;
        System.out.println(Users.count);

        users1 = new Users();
        System.out.println(Users.count);



    }

}
