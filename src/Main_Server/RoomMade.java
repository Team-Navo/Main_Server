package Main_Server;

import java.util.ArrayList;

public class RoomMade {
    private String Room_Serial;
    private ArrayList<UserMade> room;
    public RoomMade() {
        room=new ArrayList<>();
    }
    public void setThisUserAdmin(UserMade admin) { room.add(admin); }
    private void castAll(String s) {
        for(int i=0;i<room.size();i++) {
            room.get(i).castPrivate(s);
        }
    }
}
