package com.sourcery.planningpoker.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Param;

import com.sourcery.planningpoker.model.Room;
import com.sourcery.planningpoker.model.TempUser;
import com.sourcery.planningpoker.model.UserRoom;

import java.util.List;

@Mapper
public interface UserRoomMapper {

        @Select("SELECT DISTINCT room.room_id, room.room_name, room.team_name, room.sprint_name, room.description, room"
                        + ".creator_id, room"
                        + ".is_active, room.deck_id, user.user_name "
                        + "from `user-room` "
                        + "inner join room ON `user-room`.room_id=room.room_id "
                        + "inner join user ON room.creator_id=user.id "
                        + "where `user-room`.user_id = #{userId} and `user-room`.role_id = #{roleId}")
        @Results({
                        @Result(property = "roomId", column = "room_id"),
                        @Result(property = "roomName", column = "room_name"),
                        @Result(property = "teamName", column = "team_name"),
                        @Result(property = "sprintName", column = "sprint_name"),
                        @Result(property = "description", column = "description"),
                        @Result(property = "creatorId", column = "creator_id"),
                        @Result(property = "active", column = "is_active"),
                        @Result(property = "deckId", column = "deck_id"),
                        @Result(property = "creatorName", column = "user_name")
        })
        List<Room> findOwnedRoomsByIdAndRole(@Param("userId") int userId, @Param("roleId") int roleId);

        @Select("SELECT `user-room_id` FROM `user-room` ur WHERE ur.room_id = #{roomId}")
        List<Integer> findUserRoomIdByRoomIds(@Param("roomId") int roomId);

        @Select("SELECT ur.user_id, ur.role_id, u.user_name, u.email "
                        + "FROM `user-room` ur inner join user u "
                        + "on ur.user_id = u.id "
                        + "WHERE room_id = #{roomId}")
        @Results({
                        @Result(property = "userId", column = "user_id"),
                        @Result(property = "role", column = "role_id"),
                        @Result(property = "username", column = "user_name")
        })
        List<TempUser> getRoomUserList(@Param("roomId") int roomId);

        @Options(useGeneratedKeys = true, keyProperty = "userRoomId", keyColumn = "`user-room_id`")
        @Insert("INSERT INTO `user-room` (room_id, user_id, role_id) "
                        + "VALUES("
                        + "#{userRoom.roomId}, "
                        + "#{userRoom.userId}, "
                        + "#{userRoom.roleId})")
        void addRoomOwner(@Param("userRoom") UserRoom userRoom);

        @Insert("INSERT INTO `user-room` (room_id, user_id, role_id) "
                        + "VALUES("
                        + "#{roomId}, "
                        + "#{participantId}, "
                        + "#{participantRoleId})")
        void addRoomParticipant(@Param("roomId") Integer roomId,
                        @Param("participantId") Integer participantId,
                        @Param("participantRoleId") Integer participantRoleId);

        @Results({
                        @Result(property = "userRoomId", column = "user-room_id"),
                        @Result(property = "roomId", column = "room_id"),
                        @Result(property = "userId", column = "user_id"),
                        @Result(property = "roleId", column = "role_id")
        })
        @Select("SELECT * FROM `user-room` WHERE user_id = #{userId} AND room_id = #{roomId}")
        UserRoom getUserRoomByUserIdRoomId(@Param("userId") Integer userId,
                        @Param("roomId") Integer roomId);

        @Select("SELECT role_id role FROM `user-room` WHERE user_id = #{userId} AND room_id = #{roomId}")
        Integer getRoomUserRoleByIds(@Param("userId") Integer userId,  @Param("roomId") Integer roomId);

        @Results({
                        @Result(property = "userRoomId", column = "user-room_id"),
                        @Result(property = "roomId", column = "room_id"),
                        @Result(property = "userId", column = "user_id"),
                        @Result(property = "roleId", column = "role_id")
        })
        @Select("SELECT * FROM `user-room` WHERE room_id = #{roomId} AND role_id = #{roleId}")
        List<UserRoom> getUserRoomsByRoomIdRoleId(@Param("roomId") Integer roomId,
                        @Param("roleId") Integer roleId);

        @Results({
                        @Result(property = "userRoomId", column = "user-room_id"),
                        @Result(property = "roomId", column = "room_id"),
                        @Result(property = "userId", column = "user_id"),
                        @Result(property = "roleId", column = "role_id")
        })
        @Select("SELECT * FROM `user-room` WHERE user_id = #{userId} AND room_id = #{roomId} AND role_id = #{roleId}")
        UserRoom getUserRoomByUserIdRoomIdRoleId(@Param("userId") Integer userId,
                        @Param("roomId") Integer roomId,
                        @Param("roleId") Integer roleId);

        @Delete("DELETE FROM `user-room` "
                        + "WHERE `user-room_id` = #{userRoomId}")
        void deleteRoomParticipantById(Integer userRoomId);

        @Select("SELECT `user_id` FROM `user-room` ur WHERE ur.room_id = #{roomId} and ur.role_id = 3")
        List<Integer> getParticipantUserIds(@Param("roomId") Integer roomId);

}
