package org.calculator.materialprice;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.calculator.materialprice.domain.SteelGrades;
import org.calculator.materialprice.domain.SteelStandard;
import org.calculator.materialprice.service.InitService;
import org.hibernate.Hibernate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Set;

@SpringBootApplication
public class MaterialPriceApplication {

    public static void main(String[] args) {
// 1. Запуск Spring Boot и получение контекста
        ConfigurableApplicationContext context = SpringApplication.run(MaterialPriceApplication.class, args);

        // 2. Получение бина InitService из контекста
        InitService initService = context.getBean(InitService.class);


        try {
            initService.createInitialData();
//            entityManager.getTransaction().begin();
//
//            SteelStandard gost5520 = new SteelStandard(
//                    "ГОСТ 5520-79",
//                    "ГОСТ 5520-79: Углеродистая качественная сталь для котлов и сосудов под давлением",
//                    "https://gost.ru/document/125860",
//                    "GOST 5520-79.pdf",
//                    ""
//            );
//            entityManager.persist(gost5520);
//
//            SteelStandard gost5781 = new SteelStandard(
//                    "ГОСТ 5781-82",
//                    "ГОСТ 5781-82: Арматурная низколегированная сталь",
//                    "https://gost.ru/document/125863",
//                    "GOST 5781-82.pdf",
//                    ""
//            );
//            entityManager.persist(gost5781);
//
//            SteelStandard gost19281 = new SteelStandard(
//                    "ГОСТ 19281-89",
//                    "ГОСТ 19281-89: Сталь низколегированная повышенной прочности",
//                    "https://gost.ru/document/125862",
//                    "GOST 19281-89.pdf",
//                    ""
//            );
//            entityManager.persist(gost19281);
//
//            SteelStandard gost1414 = new SteelStandard(
//                    "ГОСТ 1414-75",
//                    "ГОСТ 1414-75: Конструкционная сталь высокой обрабатываемости резанием",
//                    "https://gost.ru/document/125861",
//                    "GOST 1414-75.pdf",
//                    ""
//            );
//            entityManager.persist(gost1414);
//
//            SteelGrades st3 = new SteelGrades(
//                    "Ст3кп",
//                    "Сталь 'три', кипящая",
//                    "Ст3пс",
//                    "Марка Ст3кп - сталь сваривается без ограничений, для толщины более 36 мм рекомендуется подогрев, далее термообработка.",
//                    "Применяется для второстепенных малонагруженных элементов металлоконструкций, работающих при температуре от -10°С до 400°С.",
//                    7850.0
//            );
//            st3.setStandards(Set.of(gost1414, gost5520));
//            entityManager.persist(st3);
//
//            SteelGrades st55 = new SteelGrades(
//                    "55",
//                    "Сталь качественная, содержит около 0.55% углерода",
//                    "50,60,50Г",
//                    "Марка 55 - сталь не сваривается, не применяется для сварных металлоконструкций.",
//                    "Изготовление деталей работающих на трение: гусеницы, муфты сцепления коробок передач, корпуса форсунок.",
//                    7820.0
//            );
//            st55.setStandards(Set.of(gost5520));
//            entityManager.persist(st55);
//
//            SteelGrades st10 = new SteelGrades(
//                    "10пс",
//                    "Сталь качественная, 0.10% С, кипящая/полуспокойная",
//                    "08кп15кп,10",
//                    "Марка 10кп, 10пс - сталь сваривается без ограничений, за исключением металлоконструкций после химико-термической обработки.",
//                    "Производство деталей работающих при температуре до 450°С, имеющих высокую пластичность, втулки, ушки, шайбы, винты, а также детали с высокой поверхностной твердостью, износостойкостью, имеющие высокую прочность сердцевины.",
//                    7856.0
//            );
//            st10.setStandards(Set.of(gost5520, gost1414, gost5781));
//            entityManager.persist(st10);
//
//            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Rolling back transaction");
            System.out.println(e.getMessage());
        }

    }
}
