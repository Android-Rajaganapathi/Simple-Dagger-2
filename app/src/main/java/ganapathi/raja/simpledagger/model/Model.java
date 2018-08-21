package ganapathi.raja.simpledagger.model;

import java.util.ArrayList;
import java.util.List;

public class Model {

    private List<UserInfo> userList;

    public void bindUserList() {
        userList = new ArrayList<>();
        for (int i = 1; i < 10; i++)
            userList.add(new UserInfo(i, "Name " + i, i + " Phone " + i));
    }

    public List<UserInfo> getUserList() {
        return userList;
    }
}
