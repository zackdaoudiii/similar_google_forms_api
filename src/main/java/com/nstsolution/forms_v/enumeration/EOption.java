package com.nstsolution.forms_v.enumeration;

public enum EOption {
    MOPT(0l,"MOPT", MOptValidator.class),
    COPT(3l,"COPT", MOptValidator.class),
    SOPT(1l, "SOPT",MOptValidator.class);


    private long id;
    private String name;
    private Class<? extends  OptValidator>  deserealizer;

    EOption(long id, String name, Class<? extends  OptValidator> deserealizer){
        this.id = id;
        this.name = name;
        this.deserealizer = deserealizer;

    }

    public static EOption get(String type){
        for (EOption opt: EOption.values()) {
            if (opt.name().equals(type))
                    return opt;
        }
        throw new RuntimeException("Type not suported");
    }

    public void validateJson(String json){
//        OptValidator optValidator = deserealizer.getDeclaredConstructor().newInstance();
//
//        if(!optValidator.isValidate(json)){
//            throw new RuntimeException("xxxxxxx");
//        };
    }

}
interface OptValidator{

    boolean isValidate(String json);

}

class MOptValidator implements OptValidator{

    MOptValidator(){}

    @Override
    public boolean isValidate(String json) {


        return false;
    }
}