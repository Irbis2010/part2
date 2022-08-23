package model;

import model.document.Document;
import service.DocService;

import java.util.TreeSet;

public class Test {
    public static void main(String[] args) {
        String[] type = new String[3];
        type[0] = "Task";
        type[1] = "Outgoing";
        type[2] = "Incoming";
        DocService docService = new DocService();
        TreeSet<Document> allDoc = new TreeSet<Document>();
        int t;

        for (int i = 0; i < 30; i++) {
            t = (int) (Math.random() * 3);
            Document doc = docService.createDoc(type[t]);
            if (doc != null) {
                allDoc.add(doc);
            }
        }

        for (Document document : allDoc) {
            System.out.println(document.toString());

        }
    }
}
