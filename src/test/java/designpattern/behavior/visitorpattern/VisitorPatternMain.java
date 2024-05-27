package designpattern.behavior.visitorpattern;

import designpattern.behavior.visitorpattern.element.DirectoryElement;
import designpattern.behavior.visitorpattern.element.FileElement;
import designpattern.behavior.visitorpattern.visitor.NameVisitor;
import designpattern.behavior.visitorpattern.visitor.SizeVisitor;


public class VisitorPatternMain {
    public static void main(String[] args) {
        DirectoryElement rootDir = new DirectoryElement("rootDir");
        FileElement file1 = new FileElement("file1", 10);
        FileElement file2 = new FileElement("file2", 20);
        FileElement file3 = new FileElement("file3", 15);
        FileElement file4 = new FileElement("file4", 13);
        DirectoryElement dir1 = new DirectoryElement("dir1");

        rootDir.addElement(file1);
        rootDir.addElement(file2);
        dir1.addElement(file3);
        rootDir.addElement(file4);
        rootDir.addElement(dir1);

        SizeVisitor sizeVisitor = new SizeVisitor();
        System.out.println("SizeVisitor 실행");
        sizeVisitor.visit(rootDir);
        System.out.println("Total size: " + sizeVisitor.getTotalSize());
        System.out.println("\nNameVisitor 실행");
        NameVisitor nameVisitor = new NameVisitor();
        rootDir.accept(nameVisitor);
    }
}
