package com.mycompany.demoproject.Exception;

public class FileException extends BaseException{
    public FileException(String code){
        super("file."+code);
    }
    public static FileException fileNull(){

        return new FileException("null");
    }
    //user.register.email.null
    public static FileException fileMaxSize(){
        return new FileException("max.size");
    }
    public static FileException unsupported(){
        return new FileException("unsupported.file.type");
    }


}
