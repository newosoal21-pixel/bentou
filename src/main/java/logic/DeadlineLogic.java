package logic;

import dao.DeadlineDAO;

public class DeadlineLogic {
    private DeadlineDAO dao = new DeadlineDAO();

    // 締め切りのモデルを返す
    public String getDeadline() {
        return dao.getDeadlineStr();
    }

    // 締め切りの更新処理
    public boolean updateDeadline(String newTime) {
        return dao.updateDeadline(newTime);
    }
}

