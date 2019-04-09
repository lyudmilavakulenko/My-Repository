public class WSpisok {
    private Woman[] spisok = new Woman[5];
    private  int i = 0;

    public void add (Woman w) {
        if (i<spisok.length){
            spisok[i] = w;
            System.out.println("Добавлена девушка под номером" + i);
            i++;
        }
    }
}
