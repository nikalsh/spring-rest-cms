package se.nackademin.restcms.security.policy.json;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.expression.Expression;
import org.springframework.stereotype.Component;
import se.nackademin.restcms.security.policy.PolicyDefinition;
import se.nackademin.restcms.security.policy.PolicyRule;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;


@Component("jsonFilePolicyDefinition")
public class JsonFilePolicyDefinition implements PolicyDefinition {
    private static Logger logger = LoggerFactory.getLogger(JsonFilePolicyDefinition.class);

    private static String DEFAULT_POLICY_FILE_NAME = System.getProperty("user.dir") + "/default-policy.json";


    @Value("${policy.json.filePath}")
    private String policyFilePath;

    private List<PolicyRule> rules;

    @PostConstruct
    private void init() {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Expression.class, new SpelDeserializer());
        mapper.registerModule(module);
        try {
            PolicyRule[] rulesArray = null;
            logger.debug("[init] Checking policy file at: {}", policyFilePath);
            if (policyFilePath != null && !policyFilePath.isEmpty()
                    && Files.exists(Paths.get(policyFilePath))) {
                logger.info("[init] Loading policy from custom file: {}", policyFilePath);
                rulesArray = mapper.readValue(new File(policyFilePath), PolicyRule[].class);
            } else {
                logger.info("[init] Custom policy file not found. Loading default policy");
                System.out.println(DEFAULT_POLICY_FILE_NAME);
                rulesArray = mapper.readValue(new File(DEFAULT_POLICY_FILE_NAME), PolicyRule[].class);
            }
            this.rules = (rulesArray != null ? Arrays.asList(rulesArray) : null);
            logger.info("[init] Policy loaded successfully.");
        } catch (JsonMappingException e) {
            logger.error("An error occurred while parsing the policy file.", e);
        } catch (IOException e) {
            logger.error("An error occurred while reading the policy file.", e);
        }
    }

    @Override
    public List<PolicyRule> getAllPolicyRules() {
        return rules;
    }

}
