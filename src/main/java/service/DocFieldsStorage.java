package service;

import model.document.Document;
import model.document.Incoming;
import model.document.Outgoing;
import model.document.Task;


import java.util.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Random;

public class DocFieldsStorage {
    private int id = 0;
    HashMap<Integer, String> nameOfDoc = new HashMap<Integer, String>(6);
    HashMap<Integer, String> textOfDoc = new HashMap<Integer, String>(6);
    HashMap<Integer, String> author = new HashMap<Integer, String>(6);
    HashMap<Integer, String> deliveryMethod = new HashMap<Integer, String>(6);
    public DocFieldsStorage() {
        //создание коллекции для названия документов
        nameOfDoc.put(0, "Первый документ");
        nameOfDoc.put(1, "Второй документ");
        nameOfDoc.put(2, "Третий документ");
        nameOfDoc.put(3, "Четвертый документ");
        nameOfDoc.put(4, "Пятый документ");
        nameOfDoc.put(5, "Шестой документ");
        //создание коллекции для текста
        textOfDoc.put(0, "Стихи");
        textOfDoc.put(1, "Проза");
        textOfDoc.put(2, "Роман");
        textOfDoc.put(3, "Поэма");
        textOfDoc.put(4, "Басня");
        textOfDoc.put(5, "Сатира");
        //создание коллекции для авторов документов
        author.put(0, "Толстой");
        author.put(1, "Дюма");
        author.put(2, "Мопасан");
        author.put(3, "Чехов");
        author.put(4, "Пушкин");
        author.put(5, "Крылов");
        //создание коллекции для метода доставки
        deliveryMethod.put(0, "Почта России");
        deliveryMethod.put(1, "Экспресс-одоставка");
        deliveryMethod.put(2, "ДНС");
        deliveryMethod.put(3, "Факс");
        deliveryMethod.put(4, "Сообщения по электронной почте");
        deliveryMethod.put(5, "Курьерская доставка");
    }
    //генерируем id
    public int getId() {
        id++;
        return id;
    }
    //генерируем рег номер
    public String getRegisterNumOfDoc() {
        String regNumber = String.valueOf((int) (Math.random() * 20 + 1) + " номер");
        return regNumber;
    }
    //задаем дату
    public Date getDate() {
        Calendar calendar = Calendar.getInstance();
        calendar =Calendar.getInstance();
        calendar.set(Calendar.MILLISECOND,0);
        Date regDate = calendar.getTime();
        return regDate;
    }
    public Date generateDate(){
        Calendar cal = Calendar.getInstance();
        int month=(int)(Math.random()*12);
        int day=(int)(Math.random()*29+1);
        cal.set(2022, month, day);
        Date date = cal.getTime();
        return date;
    }
    public boolean getControl(){
        Random random = new Random();
        return random.nextBoolean();
    }
    public void saveDocField(Document doc){
        doc.setText(textOfDoc.get((int)(Math.random() * 6)));
        doc.setAuthor(author.get((int)(Math.random() * 6)));
        doc.setId(getId());
        doc.setName(nameOfDoc.get((int)(Math.random() * 6)));
        // определяем тип документа, в зависимости от этого заполняем данные
        if (doc instanceof Incoming){
            ((Incoming) doc).setSender(author.get((int)(Math.random() * 6)));
            ((Incoming) doc).setDestination(author.get((int)(Math.random() * 6)));
            ((Incoming) doc).setIncomeNumber((int)(Math.random()*6));
            ((Incoming) doc).setIncomeRegistrationDate(generateDate());
        }else if (doc instanceof Outgoing){
            ((Outgoing) doc).setDestination(author.get((int)(Math.random() * 6)));
            ((Outgoing) doc).setDeliveryMethod(deliveryMethod.get((int)(Math.random() * 6)));
        }else if (doc instanceof Task){
            ((Task) doc).setDateOfIssueOrder(generateDate());
            ((Task) doc).setTermOfExecutionOrder(generateDate());
            ((Task) doc).setExecutorName(author.get((int)(Math.random() * 6)));
            ((Task) doc).setControl(getControl());
            ((Task) doc).setControllerName(author.get((int)(Math.random() * 6)));
        }
    }
}
