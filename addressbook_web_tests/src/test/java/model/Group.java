package model;

public record Group(String name, String header, String footer) {

    public Group(){
        this("", "","");
    }

    public Group withName(String name) {
        return new Group(name, this.header, this.footer);
    }

    public Group withHeader(String header) {
        return new Group(this.name, header, this.footer);
    }

    public Group withFooter(String footer) {
        return new Group(this.name, this.header, footer);
    }
}