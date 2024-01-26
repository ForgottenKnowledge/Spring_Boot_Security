package ru.spring.boot_security.demo.service.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.spring.boot_security.demo.model.Role;
import ru.spring.boot_security.demo.model.User;
import ru.spring.boot_security.demo.service.RoleService;
import ru.spring.boot_security.demo.service.UserService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Arrays;

@Service //аннотация помечает бин как сервис
@Transactional
public class InitiateUtils implements CommandLineRunner { //имплементируем интерфейс CommandLineRunner (командная строка запуска)

    private static final Logger LOGGER = LoggerFactory.getLogger(InitiateUtils.class);

    @PersistenceContext
    private EntityManager entityManager;

    private UserService userService;
    private RoleService roleService;

    public InitiateUtils(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    //переопределяем метод, который позволит
    //нам выполнять методы нашего приложения при запуске
    public void run(String... args) throws Exception {
        createRoles();
        createUsersWithRoles();

        LOGGER.info("Добавление в БД пользователя с ролью \"Администратор\" c login=1, password = 1 завершено");
        LOGGER.info("Добавление в БД пользователя с ролью \"Пользователь\" c login=2, password = 2 завершено");
    }

    /**
     * Создание ролей в БД
     */
    public void createRoles() {
        //Добавление в БД роли "Администратор" c id=1
        Role adminRole = new Role();
        adminRole.setName("ROLE_ADMIN");
        entityManager.persist(adminRole);

        //Добавление в БД роли "Пользователь" c id=2
        Role userRole = new Role();
        userRole.setName("ROLE_USER");
        entityManager.persist(userRole);

        LOGGER.info("Роли созданы");
    }

    /**
     * Создание пользователей в БД
     */
    private void createUsersWithRoles() {
        User admin = new User("1", "1", 1, 1L, "1");
        //Добавление в БД пользователя с ролью "Администратор" c login=1, password = 1
        userService.saveNewUser(admin, roleService.findRoles(new ArrayList<>(Arrays.asList(1L))));

        User user = new User("2", "2", 2, 2L, "2");
        //Добавление в БД пользователя с ролью "Пользователь" c login=2, password = 2
        userService.saveNewUser(user, roleService.findRoles(new ArrayList<>(Arrays.asList(2L))));

        LOGGER.info("Пользователи созданы");
    }
}