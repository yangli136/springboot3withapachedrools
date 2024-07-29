package com.abcdef.springboot3withdrools.config;

import java.io.File;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.runtime.KieContainer;
import org.kie.internal.io.ResourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DroolsConfiguration {

    @Bean
    KieContainer kieContainer() {
        final KieServices kieServices = KieServices.Factory.get();
        final KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        loadFileBasedRules(kieFileSystem);
        final KieBuilder kb = kieServices.newKieBuilder(kieFileSystem);
        kb.buildAll();
        KieModule kieModule = kb.getKieModule();
        final KieContainer kieContainer = kieServices.newKieContainer(kieModule.getReleaseId());
        return kieContainer;
    }

    private void loadFileBasedRules(final KieFileSystem kieFileSystem) {
        final File dir = new File("src/main/resources/rules");
        final File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                kieFileSystem.write(ResourceFactory.newFileResource(child));
            }
        }
    }

//
//    @Bean
//    RuleUnitInstance<SyntheticRuleUnit> loadRuleUintDefinition(SyntheticRuleUnit unit) {
//        RuleUnitInstance<SyntheticRuleUnit> unitInstance = RuleUnitProvider.get().createRuleUnitInstance(unit);
//        return unitInstance;
//    }
//
//    @Bean
//    SyntheticRuleUnit createSyntheticRuleUnit() {
//        DataStore<String> strings = DataSource.createStore();
//        DataStore<Integer> ints = DataSource.createStore();
//        List<String> results = new ArrayList<>();
//
//        return SyntheticRuleUnitBuilder.build("HelloWorld").registerDataSource("strings", strings, String.class)
//                .registerDataSource("ints", ints, Integer.class).registerGlobal("results", results)
//                .defineRules(rulesFactory -> {
//                    // /strings[ this == "Hello World" ]
//                    rulesFactory.rule().on(strings).filter(EQUAL, "Hello World") // when no extractor is provided "this" is implicit
//                            .execute(results, r -> r.add("it worked!")); // the consequence can ignore the matched facts
//
//                    // /strings[ length > 5 ]
//                    rulesFactory.rule().on(strings) // since the datasource has been already initialized its class can be inferred without the need of explicitly passing it
//                            .filter(s -> s.length(), GREATER_THAN, 5) // when no property name is provided it's impossible to generate indexes and property reactivity
//                            .execute(results, (r, s) -> r.add("it also worked with " + s.toUpperCase())); // this consequence also uses the matched fact
//
//                    // /strings[ length < 5 ]
//                    rulesFactory.rule("MyRule") // it is possible to optionally set a name for the rule
//                            .on(strings).filter("length", s -> s.length(), LESS_THAN, 5) // providing the name of the property used in the constraint allows index and property reactivity generation
//                            .execute(results, r -> r.add("this shouldn't fire"));
//
//                    // $s: /strings[ length > 5 ]
//                    // /ints[ this > 5, this == $s.length ]
//                    rulesFactory.rule().on(strings).filter("length", s -> s.length() > 5) // it is also possible to use a plain lambda predicate, but in this case no index can be generated
//                            .join(rule -> rule.on(ints) // creates a new pattern ...
//                                    .filter(GREATER_THAN, 5) // ... add an alpha filter to it
//                    ) // ... and join it with the former one
//                            .filter(EQUAL, String::length) // this filter is applied to the result of the join, so it is a beta constraint
//                            .execute(results, (r, s, i) -> r.add("String '" + s + "' is " + i + " characters long")); // the consequence captures all the joined variables positionally
//                });
//    }
}