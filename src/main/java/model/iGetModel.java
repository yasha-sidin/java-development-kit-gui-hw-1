package model;

import java.io.IOException;
import java.util.List;

public interface iGetModel<T> {
    List<T> getModel() throws IOException;
    void setData(T data);
}
