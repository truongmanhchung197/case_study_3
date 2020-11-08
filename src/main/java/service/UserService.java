package service;

import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserService implements IUserService{

    Connection connection = ConnectionDB.getConnection();

    private static final String INSERT_USER_SQL = "INSERT INTO user (name,userAcc,userPass,avatarCover,avatar) VALUES (?,?,?,?,?);";
    private static final String SELECT_USER_BY_USERACC = "SELECT * FROM user WHERE userAcc = ?;";
    private static final String CHECK_LOGIN = "SELECT * FROM user WHERE userAcc = ? and userPass = ?;";
//    private static final String CHECK_USERNAME = "SELECT * FROM user WHERE userAcc = ?;";
    private static final String SELECT_USER_FOR_USERNAME = "SELECT * FROM user WHERE userAcc = ?;";
    private static final String UPDATE_USER_SQL= "update User set avatarCover = ?, avatar = ? where userAcc = ?"


    private static final String SELECT_ALL_USERS = "SELECT * FROM user";

    @Override
    public boolean insertUser(User user) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(INSERT_USER_SQL);
        ps.setString(1,user.getName());
        ps.setString(2,user.getUserAccount());
        ps.setString(3,user.getUserPassword());
        ps.setString(4,user.getAvatarCover());
        ps.setString(5,user.getAvatar());
        int i = ps.executeUpdate();
        return i > 0;
    }

    public boolean checkLogin(String userName, String password){
        try {
            PreparedStatement ps = connection.prepareStatement(CHECK_LOGIN);
            ps.setString(1,userName);
            ps.setString(2,password);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()){
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public User selectUser(String userAcc) {
        User user = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_USERACC);
            preparedStatement.setString(1,userAcc);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                String name = resultSet.getString("name");
                String userAcc = resultSet.getString("userAcc");
                String userPass = resultSet.getString("userPass");
                String avatarCover = resultSet.getString("avatarCover");
                String avatar = resultSet.getString("avatar");
                user = new User( id,  name,  userAcc,  userPass,  avatarCover,  avatar);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

//    @Override
//    public List<User> selectAllUser() {
//        return null;
//    }

//    @Override
//    public boolean deleteUser(int id) throws SQLException {
//        return false;
//    }

    @Override
    public void updateUser(User user) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(UPDATE_USER_SQL);
        ps.setString(1,user.getAvatarCover());
        ps.setString(2,user.getAvatar());
        ps.setString(3,user.getUserAccount());
        ps.executeUpdate();
    }

//    @Override
//    public boolean checkUserName(String userAcc) {
//        try {
//            PreparedStatement ps = connection.prepareStatement(CHECK_USERNAME);
//            ps.setString(1,userAcc);
//            ResultSet resultSet = ps.executeQuery();
//            if (resultSet.next()){
//                return false;
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        return true;
//    }
    public User getUserFromUserName(String userAcc) {
        try {
            PreparedStatement ps = connection.prepareStatement(SELECT_USER_FOR_USERNAME);
            ps.setString(1,userAcc);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()){
                int id = resultSet.getInt("idUser");
                String name = resultSet.getString("name");
                String userPass = resultSet.getString("userPass");
                String avatarCover = resultSet.getString("avatarCover");
                String avatar = resultSet.getString("avatar");

                return new User(id,name,userAcc,userPass,avatarCover,avatar);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
