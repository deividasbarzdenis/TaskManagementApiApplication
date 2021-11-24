package lt.debarz.taskmanagementapi.user.model;

import lt.debarz.taskmanagementapi.user.controller.UserController;
import lt.debarz.taskmanagementapi.user.entity.User;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

public class UserModelAssembler extends RepresentationModelAssemblerSupport<User, UserModel> {


    public UserModelAssembler() {
        super(UserController.class, UserModel.class);
    }

    @Override
    protected UserModel instantiateModel(User user) {
        return new UserModel(user);
    }

    @Override
    public UserModel toModel(User user) {
        return createModelWithId(user.getId(), user);
    }
}
