package logic;

import dao.DeadlineDAO;
import model.OrderDeadline;

public class DeadlineLogic {
    private DeadlineDAO dao = new DeadlineDAO();

    // 締め切りのモデルを返す
    public OrderDeadline getDeadline() {
        return dao.getDeadline();
    }

    // 締め切りの更新処理
    public boolean updateDeadline(String newTime) {
        return dao.updateDeadline(newTime);
    }
}

