package com.solodive.championselect.web.rest;

import com.solodive.championselect.ChampionSelectApp;

import com.solodive.championselect.domain.LeagueAccount;
import com.solodive.championselect.repository.LeagueAccountRepository;
import com.solodive.championselect.service.LeagueAccountService;
import com.solodive.championselect.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;


import static com.solodive.championselect.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.solodive.championselect.domain.enumeration.Server;
import com.solodive.championselect.domain.enumeration.Division;
import com.solodive.championselect.domain.enumeration.Tier;
/**
 * Test class for the LeagueAccountResource REST controller.
 *
 * @see LeagueAccountResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ChampionSelectApp.class)
public class LeagueAccountResourceIntTest {

    private static final Long DEFAULT_SUMMONERS_ID = 1L;
    private static final Long UPDATED_SUMMONERS_ID = 2L;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_LEVEL = 1;
    private static final Integer UPDATED_LEVEL = 2;

    private static final Server DEFAULT_SERVER = Server.EUNE;
    private static final Server UPDATED_SERVER = Server.EUW;

    private static final Division DEFAULT_DIVISION = Division.IRON;
    private static final Division UPDATED_DIVISION = Division.BRONZE;

    private static final Tier DEFAULT_TIER = Tier.I;
    private static final Tier UPDATED_TIER = Tier.II;

    private static final Long DEFAULT_LP = 1L;
    private static final Long UPDATED_LP = 2L;

    private static final Boolean DEFAULT_LATEST = false;
    private static final Boolean UPDATED_LATEST = true;

    private static final Instant DEFAULT_LAST_UPDATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LAST_UPDATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private LeagueAccountRepository leagueAccountRepository;
    
    @Autowired
    private LeagueAccountService leagueAccountService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restLeagueAccountMockMvc;

    private LeagueAccount leagueAccount;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final LeagueAccountResource leagueAccountResource = new LeagueAccountResource(leagueAccountService);
        this.restLeagueAccountMockMvc = MockMvcBuilders.standaloneSetup(leagueAccountResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LeagueAccount createEntity(EntityManager em) {
        LeagueAccount leagueAccount = new LeagueAccount()
            .summonersId(DEFAULT_SUMMONERS_ID)
            .name(DEFAULT_NAME)
            .level(DEFAULT_LEVEL)
            .server(DEFAULT_SERVER)
            .division(DEFAULT_DIVISION)
            .tier(DEFAULT_TIER)
            .lp(DEFAULT_LP)
            .latest(DEFAULT_LATEST)
            .lastUpdate(DEFAULT_LAST_UPDATE);
        return leagueAccount;
    }

    @Before
    public void initTest() {
        leagueAccount = createEntity(em);
    }

    @Test
    @Transactional
    public void createLeagueAccount() throws Exception {
        int databaseSizeBeforeCreate = leagueAccountRepository.findAll().size();

        // Create the LeagueAccount
        restLeagueAccountMockMvc.perform(post("/api/league-accounts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(leagueAccount)))
            .andExpect(status().isCreated());

        // Validate the LeagueAccount in the database
        List<LeagueAccount> leagueAccountList = leagueAccountRepository.findAll();
        assertThat(leagueAccountList).hasSize(databaseSizeBeforeCreate + 1);
        LeagueAccount testLeagueAccount = leagueAccountList.get(leagueAccountList.size() - 1);
        assertThat(testLeagueAccount.getSummonersId()).isEqualTo(DEFAULT_SUMMONERS_ID);
        assertThat(testLeagueAccount.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testLeagueAccount.getLevel()).isEqualTo(DEFAULT_LEVEL);
        assertThat(testLeagueAccount.getServer()).isEqualTo(DEFAULT_SERVER);
        assertThat(testLeagueAccount.getDivision()).isEqualTo(DEFAULT_DIVISION);
        assertThat(testLeagueAccount.getTier()).isEqualTo(DEFAULT_TIER);
        assertThat(testLeagueAccount.getLp()).isEqualTo(DEFAULT_LP);
        assertThat(testLeagueAccount.isLatest()).isEqualTo(DEFAULT_LATEST);
        assertThat(testLeagueAccount.getLastUpdate()).isEqualTo(DEFAULT_LAST_UPDATE);
    }

    @Test
    @Transactional
    public void createLeagueAccountWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = leagueAccountRepository.findAll().size();

        // Create the LeagueAccount with an existing ID
        leagueAccount.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLeagueAccountMockMvc.perform(post("/api/league-accounts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(leagueAccount)))
            .andExpect(status().isBadRequest());

        // Validate the LeagueAccount in the database
        List<LeagueAccount> leagueAccountList = leagueAccountRepository.findAll();
        assertThat(leagueAccountList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllLeagueAccounts() throws Exception {
        // Initialize the database
        leagueAccountRepository.saveAndFlush(leagueAccount);

        // Get all the leagueAccountList
        restLeagueAccountMockMvc.perform(get("/api/league-accounts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(leagueAccount.getId().intValue())))
            .andExpect(jsonPath("$.[*].summonersId").value(hasItem(DEFAULT_SUMMONERS_ID.intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].level").value(hasItem(DEFAULT_LEVEL)))
            .andExpect(jsonPath("$.[*].server").value(hasItem(DEFAULT_SERVER.toString())))
            .andExpect(jsonPath("$.[*].division").value(hasItem(DEFAULT_DIVISION.toString())))
            .andExpect(jsonPath("$.[*].tier").value(hasItem(DEFAULT_TIER.toString())))
            .andExpect(jsonPath("$.[*].lp").value(hasItem(DEFAULT_LP.intValue())))
            .andExpect(jsonPath("$.[*].latest").value(hasItem(DEFAULT_LATEST.booleanValue())))
            .andExpect(jsonPath("$.[*].lastUpdate").value(hasItem(DEFAULT_LAST_UPDATE.toString())));
    }
    
    @Test
    @Transactional
    public void getLeagueAccount() throws Exception {
        // Initialize the database
        leagueAccountRepository.saveAndFlush(leagueAccount);

        // Get the leagueAccount
        restLeagueAccountMockMvc.perform(get("/api/league-accounts/{id}", leagueAccount.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(leagueAccount.getId().intValue()))
            .andExpect(jsonPath("$.summonersId").value(DEFAULT_SUMMONERS_ID.intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.level").value(DEFAULT_LEVEL))
            .andExpect(jsonPath("$.server").value(DEFAULT_SERVER.toString()))
            .andExpect(jsonPath("$.division").value(DEFAULT_DIVISION.toString()))
            .andExpect(jsonPath("$.tier").value(DEFAULT_TIER.toString()))
            .andExpect(jsonPath("$.lp").value(DEFAULT_LP.intValue()))
            .andExpect(jsonPath("$.latest").value(DEFAULT_LATEST.booleanValue()))
            .andExpect(jsonPath("$.lastUpdate").value(DEFAULT_LAST_UPDATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingLeagueAccount() throws Exception {
        // Get the leagueAccount
        restLeagueAccountMockMvc.perform(get("/api/league-accounts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLeagueAccount() throws Exception {
        // Initialize the database
        leagueAccountService.save(leagueAccount);

        int databaseSizeBeforeUpdate = leagueAccountRepository.findAll().size();

        // Update the leagueAccount
        LeagueAccount updatedLeagueAccount = leagueAccountRepository.findById(leagueAccount.getId()).get();
        // Disconnect from session so that the updates on updatedLeagueAccount are not directly saved in db
        em.detach(updatedLeagueAccount);
        updatedLeagueAccount
            .summonersId(UPDATED_SUMMONERS_ID)
            .name(UPDATED_NAME)
            .level(UPDATED_LEVEL)
            .server(UPDATED_SERVER)
            .division(UPDATED_DIVISION)
            .tier(UPDATED_TIER)
            .lp(UPDATED_LP)
            .latest(UPDATED_LATEST)
            .lastUpdate(UPDATED_LAST_UPDATE);

        restLeagueAccountMockMvc.perform(put("/api/league-accounts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedLeagueAccount)))
            .andExpect(status().isOk());

        // Validate the LeagueAccount in the database
        List<LeagueAccount> leagueAccountList = leagueAccountRepository.findAll();
        assertThat(leagueAccountList).hasSize(databaseSizeBeforeUpdate);
        LeagueAccount testLeagueAccount = leagueAccountList.get(leagueAccountList.size() - 1);
        assertThat(testLeagueAccount.getSummonersId()).isEqualTo(UPDATED_SUMMONERS_ID);
        assertThat(testLeagueAccount.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testLeagueAccount.getLevel()).isEqualTo(UPDATED_LEVEL);
        assertThat(testLeagueAccount.getServer()).isEqualTo(UPDATED_SERVER);
        assertThat(testLeagueAccount.getDivision()).isEqualTo(UPDATED_DIVISION);
        assertThat(testLeagueAccount.getTier()).isEqualTo(UPDATED_TIER);
        assertThat(testLeagueAccount.getLp()).isEqualTo(UPDATED_LP);
        assertThat(testLeagueAccount.isLatest()).isEqualTo(UPDATED_LATEST);
        assertThat(testLeagueAccount.getLastUpdate()).isEqualTo(UPDATED_LAST_UPDATE);
    }

    @Test
    @Transactional
    public void updateNonExistingLeagueAccount() throws Exception {
        int databaseSizeBeforeUpdate = leagueAccountRepository.findAll().size();

        // Create the LeagueAccount

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLeagueAccountMockMvc.perform(put("/api/league-accounts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(leagueAccount)))
            .andExpect(status().isBadRequest());

        // Validate the LeagueAccount in the database
        List<LeagueAccount> leagueAccountList = leagueAccountRepository.findAll();
        assertThat(leagueAccountList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLeagueAccount() throws Exception {
        // Initialize the database
        leagueAccountService.save(leagueAccount);

        int databaseSizeBeforeDelete = leagueAccountRepository.findAll().size();

        // Get the leagueAccount
        restLeagueAccountMockMvc.perform(delete("/api/league-accounts/{id}", leagueAccount.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<LeagueAccount> leagueAccountList = leagueAccountRepository.findAll();
        assertThat(leagueAccountList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LeagueAccount.class);
        LeagueAccount leagueAccount1 = new LeagueAccount();
        leagueAccount1.setId(1L);
        LeagueAccount leagueAccount2 = new LeagueAccount();
        leagueAccount2.setId(leagueAccount1.getId());
        assertThat(leagueAccount1).isEqualTo(leagueAccount2);
        leagueAccount2.setId(2L);
        assertThat(leagueAccount1).isNotEqualTo(leagueAccount2);
        leagueAccount1.setId(null);
        assertThat(leagueAccount1).isNotEqualTo(leagueAccount2);
    }
}
