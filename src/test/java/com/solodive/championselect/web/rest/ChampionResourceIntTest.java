package com.solodive.championselect.web.rest;

import com.solodive.championselect.ChampionSelectApp;

import com.solodive.championselect.domain.Champion;
import com.solodive.championselect.repository.ChampionRepository;
import com.solodive.championselect.service.ChampionService;
import com.solodive.championselect.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
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
import java.util.ArrayList;
import java.util.List;


import static com.solodive.championselect.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.solodive.championselect.domain.enumeration.ChampionResourceType;
import com.solodive.championselect.domain.enumeration.ChampionResourceType;
/**
 * Test class for the ChampionResource REST controller.
 *
 * @see ChampionResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ChampionSelectApp.class)
public class ChampionResourceIntTest {

    private static final Long DEFAULT_INTERNAL_VERSION = 1L;
    private static final Long UPDATED_INTERNAL_VERSION = 2L;

    private static final Instant DEFAULT_LAST_MODIFIED = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LAST_MODIFIED = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_RIOT_ID = "AAAAAAAAAA";
    private static final String UPDATED_RIOT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_RIOT_KEY = "AAAAAAAAAA";
    private static final String UPDATED_RIOT_KEY = "BBBBBBBBBB";

    private static final String DEFAULT_VERSION = "AAAAAAAAAA";
    private static final String UPDATED_VERSION = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_BLURB = "AAAAAAAAAA";
    private static final String UPDATED_BLURB = "BBBBBBBBBB";

    private static final Long DEFAULT_ATTACK = 1L;
    private static final Long UPDATED_ATTACK = 2L;

    private static final Long DEFAULT_DEFENCE = 1L;
    private static final Long UPDATED_DEFENCE = 2L;

    private static final Long DEFAULT_MAGIC = 1L;
    private static final Long UPDATED_MAGIC = 2L;

    private static final Long DEFAULT_DIFFICULTY = 1L;
    private static final Long UPDATED_DIFFICULTY = 2L;

    private static final Double DEFAULT_MOVEMENT_SPEED = 1D;
    private static final Double UPDATED_MOVEMENT_SPEED = 2D;

    private static final Double DEFAULT_ATTACK_RANGE = 1D;
    private static final Double UPDATED_ATTACK_RANGE = 2D;

    private static final Double DEFAULT_ARMOR_FLAT = 1D;
    private static final Double UPDATED_ARMOR_FLAT = 2D;

    private static final Double DEFAULT_ARMOR_PER_LEVEL = 1D;
    private static final Double UPDATED_ARMOR_PER_LEVEL = 2D;

    private static final Double DEFAULT_SPELL_BLOCK_FLAT = 1D;
    private static final Double UPDATED_SPELL_BLOCK_FLAT = 2D;

    private static final Double DEFAULT_SPELL_BLOCK_PER_LEVEL = 1D;
    private static final Double UPDATED_SPELL_BLOCK_PER_LEVEL = 2D;

    private static final Double DEFAULT_CRITICAL_FLAT = 1D;
    private static final Double UPDATED_CRITICAL_FLAT = 2D;

    private static final Double DEFAULT_CRITICAL_PER_LEVEL = 1D;
    private static final Double UPDATED_CRITICAL_PER_LEVEL = 2D;

    private static final Double DEFAULT_ATTACK_DAMAGE_FLAT = 1D;
    private static final Double UPDATED_ATTACK_DAMAGE_FLAT = 2D;

    private static final Double DEFAULT_ATTACK_DAMAGE_PER_LEVEL = 1D;
    private static final Double UPDATED_ATTACK_DAMAGE_PER_LEVEL = 2D;

    private static final Double DEFAULT_ATTACK_SPEED_FLAT = 1D;
    private static final Double UPDATED_ATTACK_SPEED_FLAT = 2D;

    private static final Double DEFAULT_ATTACK_SPEED_PER_LEVEL = 1D;
    private static final Double UPDATED_ATTACK_SPEED_PER_LEVEL = 2D;

    private static final ChampionResourceType DEFAULT_RESOURCE_FIRST_TYPE = ChampionResourceType.HEALTH;
    private static final ChampionResourceType UPDATED_RESOURCE_FIRST_TYPE = ChampionResourceType.MANA;

    private static final ChampionResourceType DEFAULT_RESOURCE_SECOND_TYPE = ChampionResourceType.HEALTH;
    private static final ChampionResourceType UPDATED_RESOURCE_SECOND_TYPE = ChampionResourceType.MANA;

    private static final Double DEFAULT_RESOURCE_FIRST_POOL_FLAT = 1D;
    private static final Double UPDATED_RESOURCE_FIRST_POOL_FLAT = 2D;

    private static final Double DEFAULT_RESOURCE_FIRST_POOL_PER_LEVEL = 1D;
    private static final Double UPDATED_RESOURCE_FIRST_POOL_PER_LEVEL = 2D;

    private static final Double DEFAULT_RESOURCE_FIRST_REGENERATION_FLAT = 1D;
    private static final Double UPDATED_RESOURCE_FIRST_REGENERATION_FLAT = 2D;

    private static final Double DEFAULT_RESOURCE_FIRST_REGENERATION_PER_LEVEL = 1D;
    private static final Double UPDATED_RESOURCE_FIRST_REGENERATION_PER_LEVEL = 2D;

    private static final Double DEFAULT_RESOURCE_SECOND_POOL_FLAT = 1D;
    private static final Double UPDATED_RESOURCE_SECOND_POOL_FLAT = 2D;

    private static final Double DEFAULT_RESOURCE_SECOND_POOL_PER_LEVEL = 1D;
    private static final Double UPDATED_RESOURCE_SECOND_POOL_PER_LEVEL = 2D;

    private static final Double DEFAULT_RESOURCE_SECOND_REGENERATION_FLAT = 1D;
    private static final Double UPDATED_RESOURCE_SECOND_REGENERATION_FLAT = 2D;

    private static final Double DEFAULT_RESOURCE_SECOND_REGENERATION_PER_LEVEL = 1D;
    private static final Double UPDATED_RESOURCE_SECOND_REGENERATION_PER_LEVEL = 2D;

    @Autowired
    private ChampionRepository championRepository;

    @Mock
    private ChampionRepository championRepositoryMock;
    

    @Mock
    private ChampionService championServiceMock;

    @Autowired
    private ChampionService championService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restChampionMockMvc;

    private Champion champion;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ChampionResource championResource = new ChampionResource(championService);
        this.restChampionMockMvc = MockMvcBuilders.standaloneSetup(championResource)
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
    public static Champion createEntity(EntityManager em) {
        Champion champion = new Champion()
            .internalVersion(DEFAULT_INTERNAL_VERSION)
            .lastModified(DEFAULT_LAST_MODIFIED)
            .riotId(DEFAULT_RIOT_ID)
            .riotKey(DEFAULT_RIOT_KEY)
            .version(DEFAULT_VERSION)
            .name(DEFAULT_NAME)
            .title(DEFAULT_TITLE)
            .blurb(DEFAULT_BLURB)
            .attack(DEFAULT_ATTACK)
            .defence(DEFAULT_DEFENCE)
            .magic(DEFAULT_MAGIC)
            .difficulty(DEFAULT_DIFFICULTY)
            .movementSpeed(DEFAULT_MOVEMENT_SPEED)
            .attackRange(DEFAULT_ATTACK_RANGE)
            .armorFlat(DEFAULT_ARMOR_FLAT)
            .armorPerLevel(DEFAULT_ARMOR_PER_LEVEL)
            .spellBlockFlat(DEFAULT_SPELL_BLOCK_FLAT)
            .spellBlockPerLevel(DEFAULT_SPELL_BLOCK_PER_LEVEL)
            .criticalFlat(DEFAULT_CRITICAL_FLAT)
            .criticalPerLevel(DEFAULT_CRITICAL_PER_LEVEL)
            .attackDamageFlat(DEFAULT_ATTACK_DAMAGE_FLAT)
            .attackDamagePerLevel(DEFAULT_ATTACK_DAMAGE_PER_LEVEL)
            .attackSpeedFlat(DEFAULT_ATTACK_SPEED_FLAT)
            .attackSpeedPerLevel(DEFAULT_ATTACK_SPEED_PER_LEVEL)
            .resourceFirstType(DEFAULT_RESOURCE_FIRST_TYPE)
            .resourceSecondType(DEFAULT_RESOURCE_SECOND_TYPE)
            .resourceFirstPoolFlat(DEFAULT_RESOURCE_FIRST_POOL_FLAT)
            .resourceFirstPoolPerLevel(DEFAULT_RESOURCE_FIRST_POOL_PER_LEVEL)
            .resourceFirstRegenerationFlat(DEFAULT_RESOURCE_FIRST_REGENERATION_FLAT)
            .resourceFirstRegenerationPerLevel(DEFAULT_RESOURCE_FIRST_REGENERATION_PER_LEVEL)
            .resourceSecondPoolFlat(DEFAULT_RESOURCE_SECOND_POOL_FLAT)
            .resourceSecondPoolPerLevel(DEFAULT_RESOURCE_SECOND_POOL_PER_LEVEL)
            .resourceSecondRegenerationFlat(DEFAULT_RESOURCE_SECOND_REGENERATION_FLAT)
            .resourceSecondRegenerationPerLevel(DEFAULT_RESOURCE_SECOND_REGENERATION_PER_LEVEL);
        return champion;
    }

    @Before
    public void initTest() {
        champion = createEntity(em);
    }

    @Test
    @Transactional
    public void createChampion() throws Exception {
        int databaseSizeBeforeCreate = championRepository.findAll().size();

        // Create the Champion
        restChampionMockMvc.perform(post("/api/champions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(champion)))
            .andExpect(status().isCreated());

        // Validate the Champion in the database
        List<Champion> championList = championRepository.findAll();
        assertThat(championList).hasSize(databaseSizeBeforeCreate + 1);
        Champion testChampion = championList.get(championList.size() - 1);
        assertThat(testChampion.getInternalVersion()).isEqualTo(DEFAULT_INTERNAL_VERSION);
        assertThat(testChampion.getLastModified()).isEqualTo(DEFAULT_LAST_MODIFIED);
        assertThat(testChampion.getRiotId()).isEqualTo(DEFAULT_RIOT_ID);
        assertThat(testChampion.getRiotKey()).isEqualTo(DEFAULT_RIOT_KEY);
        assertThat(testChampion.getVersion()).isEqualTo(DEFAULT_VERSION);
        assertThat(testChampion.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testChampion.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testChampion.getBlurb()).isEqualTo(DEFAULT_BLURB);
        assertThat(testChampion.getAttack()).isEqualTo(DEFAULT_ATTACK);
        assertThat(testChampion.getDefence()).isEqualTo(DEFAULT_DEFENCE);
        assertThat(testChampion.getMagic()).isEqualTo(DEFAULT_MAGIC);
        assertThat(testChampion.getDifficulty()).isEqualTo(DEFAULT_DIFFICULTY);
        assertThat(testChampion.getMovementSpeed()).isEqualTo(DEFAULT_MOVEMENT_SPEED);
        assertThat(testChampion.getAttackRange()).isEqualTo(DEFAULT_ATTACK_RANGE);
        assertThat(testChampion.getArmorFlat()).isEqualTo(DEFAULT_ARMOR_FLAT);
        assertThat(testChampion.getArmorPerLevel()).isEqualTo(DEFAULT_ARMOR_PER_LEVEL);
        assertThat(testChampion.getSpellBlockFlat()).isEqualTo(DEFAULT_SPELL_BLOCK_FLAT);
        assertThat(testChampion.getSpellBlockPerLevel()).isEqualTo(DEFAULT_SPELL_BLOCK_PER_LEVEL);
        assertThat(testChampion.getCriticalFlat()).isEqualTo(DEFAULT_CRITICAL_FLAT);
        assertThat(testChampion.getCriticalPerLevel()).isEqualTo(DEFAULT_CRITICAL_PER_LEVEL);
        assertThat(testChampion.getAttackDamageFlat()).isEqualTo(DEFAULT_ATTACK_DAMAGE_FLAT);
        assertThat(testChampion.getAttackDamagePerLevel()).isEqualTo(DEFAULT_ATTACK_DAMAGE_PER_LEVEL);
        assertThat(testChampion.getAttackSpeedFlat()).isEqualTo(DEFAULT_ATTACK_SPEED_FLAT);
        assertThat(testChampion.getAttackSpeedPerLevel()).isEqualTo(DEFAULT_ATTACK_SPEED_PER_LEVEL);
        assertThat(testChampion.getResourceFirstType()).isEqualTo(DEFAULT_RESOURCE_FIRST_TYPE);
        assertThat(testChampion.getResourceSecondType()).isEqualTo(DEFAULT_RESOURCE_SECOND_TYPE);
        assertThat(testChampion.getResourceFirstPoolFlat()).isEqualTo(DEFAULT_RESOURCE_FIRST_POOL_FLAT);
        assertThat(testChampion.getResourceFirstPoolPerLevel()).isEqualTo(DEFAULT_RESOURCE_FIRST_POOL_PER_LEVEL);
        assertThat(testChampion.getResourceFirstRegenerationFlat()).isEqualTo(DEFAULT_RESOURCE_FIRST_REGENERATION_FLAT);
        assertThat(testChampion.getResourceFirstRegenerationPerLevel()).isEqualTo(DEFAULT_RESOURCE_FIRST_REGENERATION_PER_LEVEL);
        assertThat(testChampion.getResourceSecondPoolFlat()).isEqualTo(DEFAULT_RESOURCE_SECOND_POOL_FLAT);
        assertThat(testChampion.getResourceSecondPoolPerLevel()).isEqualTo(DEFAULT_RESOURCE_SECOND_POOL_PER_LEVEL);
        assertThat(testChampion.getResourceSecondRegenerationFlat()).isEqualTo(DEFAULT_RESOURCE_SECOND_REGENERATION_FLAT);
        assertThat(testChampion.getResourceSecondRegenerationPerLevel()).isEqualTo(DEFAULT_RESOURCE_SECOND_REGENERATION_PER_LEVEL);
    }

    @Test
    @Transactional
    public void createChampionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = championRepository.findAll().size();

        // Create the Champion with an existing ID
        champion.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restChampionMockMvc.perform(post("/api/champions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(champion)))
            .andExpect(status().isBadRequest());

        // Validate the Champion in the database
        List<Champion> championList = championRepository.findAll();
        assertThat(championList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkInternalVersionIsRequired() throws Exception {
        int databaseSizeBeforeTest = championRepository.findAll().size();
        // set the field null
        champion.setInternalVersion(null);

        // Create the Champion, which fails.

        restChampionMockMvc.perform(post("/api/champions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(champion)))
            .andExpect(status().isBadRequest());

        List<Champion> championList = championRepository.findAll();
        assertThat(championList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLastModifiedIsRequired() throws Exception {
        int databaseSizeBeforeTest = championRepository.findAll().size();
        // set the field null
        champion.setLastModified(null);

        // Create the Champion, which fails.

        restChampionMockMvc.perform(post("/api/champions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(champion)))
            .andExpect(status().isBadRequest());

        List<Champion> championList = championRepository.findAll();
        assertThat(championList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllChampions() throws Exception {
        // Initialize the database
        championRepository.saveAndFlush(champion);

        // Get all the championList
        restChampionMockMvc.perform(get("/api/champions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(champion.getId().intValue())))
            .andExpect(jsonPath("$.[*].internalVersion").value(hasItem(DEFAULT_INTERNAL_VERSION.intValue())))
            .andExpect(jsonPath("$.[*].lastModified").value(hasItem(DEFAULT_LAST_MODIFIED.toString())))
            .andExpect(jsonPath("$.[*].riotId").value(hasItem(DEFAULT_RIOT_ID.toString())))
            .andExpect(jsonPath("$.[*].riotKey").value(hasItem(DEFAULT_RIOT_KEY.toString())))
            .andExpect(jsonPath("$.[*].version").value(hasItem(DEFAULT_VERSION.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE.toString())))
            .andExpect(jsonPath("$.[*].blurb").value(hasItem(DEFAULT_BLURB.toString())))
            .andExpect(jsonPath("$.[*].attack").value(hasItem(DEFAULT_ATTACK.intValue())))
            .andExpect(jsonPath("$.[*].defence").value(hasItem(DEFAULT_DEFENCE.intValue())))
            .andExpect(jsonPath("$.[*].magic").value(hasItem(DEFAULT_MAGIC.intValue())))
            .andExpect(jsonPath("$.[*].difficulty").value(hasItem(DEFAULT_DIFFICULTY.intValue())))
            .andExpect(jsonPath("$.[*].movementSpeed").value(hasItem(DEFAULT_MOVEMENT_SPEED.doubleValue())))
            .andExpect(jsonPath("$.[*].attackRange").value(hasItem(DEFAULT_ATTACK_RANGE.doubleValue())))
            .andExpect(jsonPath("$.[*].armorFlat").value(hasItem(DEFAULT_ARMOR_FLAT.doubleValue())))
            .andExpect(jsonPath("$.[*].armorPerLevel").value(hasItem(DEFAULT_ARMOR_PER_LEVEL.doubleValue())))
            .andExpect(jsonPath("$.[*].spellBlockFlat").value(hasItem(DEFAULT_SPELL_BLOCK_FLAT.doubleValue())))
            .andExpect(jsonPath("$.[*].spellBlockPerLevel").value(hasItem(DEFAULT_SPELL_BLOCK_PER_LEVEL.doubleValue())))
            .andExpect(jsonPath("$.[*].criticalFlat").value(hasItem(DEFAULT_CRITICAL_FLAT.doubleValue())))
            .andExpect(jsonPath("$.[*].criticalPerLevel").value(hasItem(DEFAULT_CRITICAL_PER_LEVEL.doubleValue())))
            .andExpect(jsonPath("$.[*].attackDamageFlat").value(hasItem(DEFAULT_ATTACK_DAMAGE_FLAT.doubleValue())))
            .andExpect(jsonPath("$.[*].attackDamagePerLevel").value(hasItem(DEFAULT_ATTACK_DAMAGE_PER_LEVEL.doubleValue())))
            .andExpect(jsonPath("$.[*].attackSpeedFlat").value(hasItem(DEFAULT_ATTACK_SPEED_FLAT.doubleValue())))
            .andExpect(jsonPath("$.[*].attackSpeedPerLevel").value(hasItem(DEFAULT_ATTACK_SPEED_PER_LEVEL.doubleValue())))
            .andExpect(jsonPath("$.[*].resourceFirstType").value(hasItem(DEFAULT_RESOURCE_FIRST_TYPE.toString())))
            .andExpect(jsonPath("$.[*].resourceSecondType").value(hasItem(DEFAULT_RESOURCE_SECOND_TYPE.toString())))
            .andExpect(jsonPath("$.[*].resourceFirstPoolFlat").value(hasItem(DEFAULT_RESOURCE_FIRST_POOL_FLAT.doubleValue())))
            .andExpect(jsonPath("$.[*].resourceFirstPoolPerLevel").value(hasItem(DEFAULT_RESOURCE_FIRST_POOL_PER_LEVEL.doubleValue())))
            .andExpect(jsonPath("$.[*].resourceFirstRegenerationFlat").value(hasItem(DEFAULT_RESOURCE_FIRST_REGENERATION_FLAT.doubleValue())))
            .andExpect(jsonPath("$.[*].resourceFirstRegenerationPerLevel").value(hasItem(DEFAULT_RESOURCE_FIRST_REGENERATION_PER_LEVEL.doubleValue())))
            .andExpect(jsonPath("$.[*].resourceSecondPoolFlat").value(hasItem(DEFAULT_RESOURCE_SECOND_POOL_FLAT.doubleValue())))
            .andExpect(jsonPath("$.[*].resourceSecondPoolPerLevel").value(hasItem(DEFAULT_RESOURCE_SECOND_POOL_PER_LEVEL.doubleValue())))
            .andExpect(jsonPath("$.[*].resourceSecondRegenerationFlat").value(hasItem(DEFAULT_RESOURCE_SECOND_REGENERATION_FLAT.doubleValue())))
            .andExpect(jsonPath("$.[*].resourceSecondRegenerationPerLevel").value(hasItem(DEFAULT_RESOURCE_SECOND_REGENERATION_PER_LEVEL.doubleValue())));
    }
    
    public void getAllChampionsWithEagerRelationshipsIsEnabled() throws Exception {
        ChampionResource championResource = new ChampionResource(championServiceMock);
        when(championServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        MockMvc restChampionMockMvc = MockMvcBuilders.standaloneSetup(championResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restChampionMockMvc.perform(get("/api/champions?eagerload=true"))
        .andExpect(status().isOk());

        verify(championServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    public void getAllChampionsWithEagerRelationshipsIsNotEnabled() throws Exception {
        ChampionResource championResource = new ChampionResource(championServiceMock);
            when(championServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));
            MockMvc restChampionMockMvc = MockMvcBuilders.standaloneSetup(championResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restChampionMockMvc.perform(get("/api/champions?eagerload=true"))
        .andExpect(status().isOk());

            verify(championServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getChampion() throws Exception {
        // Initialize the database
        championRepository.saveAndFlush(champion);

        // Get the champion
        restChampionMockMvc.perform(get("/api/champions/{id}", champion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(champion.getId().intValue()))
            .andExpect(jsonPath("$.internalVersion").value(DEFAULT_INTERNAL_VERSION.intValue()))
            .andExpect(jsonPath("$.lastModified").value(DEFAULT_LAST_MODIFIED.toString()))
            .andExpect(jsonPath("$.riotId").value(DEFAULT_RIOT_ID.toString()))
            .andExpect(jsonPath("$.riotKey").value(DEFAULT_RIOT_KEY.toString()))
            .andExpect(jsonPath("$.version").value(DEFAULT_VERSION.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE.toString()))
            .andExpect(jsonPath("$.blurb").value(DEFAULT_BLURB.toString()))
            .andExpect(jsonPath("$.attack").value(DEFAULT_ATTACK.intValue()))
            .andExpect(jsonPath("$.defence").value(DEFAULT_DEFENCE.intValue()))
            .andExpect(jsonPath("$.magic").value(DEFAULT_MAGIC.intValue()))
            .andExpect(jsonPath("$.difficulty").value(DEFAULT_DIFFICULTY.intValue()))
            .andExpect(jsonPath("$.movementSpeed").value(DEFAULT_MOVEMENT_SPEED.doubleValue()))
            .andExpect(jsonPath("$.attackRange").value(DEFAULT_ATTACK_RANGE.doubleValue()))
            .andExpect(jsonPath("$.armorFlat").value(DEFAULT_ARMOR_FLAT.doubleValue()))
            .andExpect(jsonPath("$.armorPerLevel").value(DEFAULT_ARMOR_PER_LEVEL.doubleValue()))
            .andExpect(jsonPath("$.spellBlockFlat").value(DEFAULT_SPELL_BLOCK_FLAT.doubleValue()))
            .andExpect(jsonPath("$.spellBlockPerLevel").value(DEFAULT_SPELL_BLOCK_PER_LEVEL.doubleValue()))
            .andExpect(jsonPath("$.criticalFlat").value(DEFAULT_CRITICAL_FLAT.doubleValue()))
            .andExpect(jsonPath("$.criticalPerLevel").value(DEFAULT_CRITICAL_PER_LEVEL.doubleValue()))
            .andExpect(jsonPath("$.attackDamageFlat").value(DEFAULT_ATTACK_DAMAGE_FLAT.doubleValue()))
            .andExpect(jsonPath("$.attackDamagePerLevel").value(DEFAULT_ATTACK_DAMAGE_PER_LEVEL.doubleValue()))
            .andExpect(jsonPath("$.attackSpeedFlat").value(DEFAULT_ATTACK_SPEED_FLAT.doubleValue()))
            .andExpect(jsonPath("$.attackSpeedPerLevel").value(DEFAULT_ATTACK_SPEED_PER_LEVEL.doubleValue()))
            .andExpect(jsonPath("$.resourceFirstType").value(DEFAULT_RESOURCE_FIRST_TYPE.toString()))
            .andExpect(jsonPath("$.resourceSecondType").value(DEFAULT_RESOURCE_SECOND_TYPE.toString()))
            .andExpect(jsonPath("$.resourceFirstPoolFlat").value(DEFAULT_RESOURCE_FIRST_POOL_FLAT.doubleValue()))
            .andExpect(jsonPath("$.resourceFirstPoolPerLevel").value(DEFAULT_RESOURCE_FIRST_POOL_PER_LEVEL.doubleValue()))
            .andExpect(jsonPath("$.resourceFirstRegenerationFlat").value(DEFAULT_RESOURCE_FIRST_REGENERATION_FLAT.doubleValue()))
            .andExpect(jsonPath("$.resourceFirstRegenerationPerLevel").value(DEFAULT_RESOURCE_FIRST_REGENERATION_PER_LEVEL.doubleValue()))
            .andExpect(jsonPath("$.resourceSecondPoolFlat").value(DEFAULT_RESOURCE_SECOND_POOL_FLAT.doubleValue()))
            .andExpect(jsonPath("$.resourceSecondPoolPerLevel").value(DEFAULT_RESOURCE_SECOND_POOL_PER_LEVEL.doubleValue()))
            .andExpect(jsonPath("$.resourceSecondRegenerationFlat").value(DEFAULT_RESOURCE_SECOND_REGENERATION_FLAT.doubleValue()))
            .andExpect(jsonPath("$.resourceSecondRegenerationPerLevel").value(DEFAULT_RESOURCE_SECOND_REGENERATION_PER_LEVEL.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingChampion() throws Exception {
        // Get the champion
        restChampionMockMvc.perform(get("/api/champions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateChampion() throws Exception {
        // Initialize the database
        championService.save(champion);

        int databaseSizeBeforeUpdate = championRepository.findAll().size();

        // Update the champion
        Champion updatedChampion = championRepository.findById(champion.getId()).get();
        // Disconnect from session so that the updates on updatedChampion are not directly saved in db
        em.detach(updatedChampion);
        updatedChampion
            .internalVersion(UPDATED_INTERNAL_VERSION)
            .lastModified(UPDATED_LAST_MODIFIED)
            .riotId(UPDATED_RIOT_ID)
            .riotKey(UPDATED_RIOT_KEY)
            .version(UPDATED_VERSION)
            .name(UPDATED_NAME)
            .title(UPDATED_TITLE)
            .blurb(UPDATED_BLURB)
            .attack(UPDATED_ATTACK)
            .defence(UPDATED_DEFENCE)
            .magic(UPDATED_MAGIC)
            .difficulty(UPDATED_DIFFICULTY)
            .movementSpeed(UPDATED_MOVEMENT_SPEED)
            .attackRange(UPDATED_ATTACK_RANGE)
            .armorFlat(UPDATED_ARMOR_FLAT)
            .armorPerLevel(UPDATED_ARMOR_PER_LEVEL)
            .spellBlockFlat(UPDATED_SPELL_BLOCK_FLAT)
            .spellBlockPerLevel(UPDATED_SPELL_BLOCK_PER_LEVEL)
            .criticalFlat(UPDATED_CRITICAL_FLAT)
            .criticalPerLevel(UPDATED_CRITICAL_PER_LEVEL)
            .attackDamageFlat(UPDATED_ATTACK_DAMAGE_FLAT)
            .attackDamagePerLevel(UPDATED_ATTACK_DAMAGE_PER_LEVEL)
            .attackSpeedFlat(UPDATED_ATTACK_SPEED_FLAT)
            .attackSpeedPerLevel(UPDATED_ATTACK_SPEED_PER_LEVEL)
            .resourceFirstType(UPDATED_RESOURCE_FIRST_TYPE)
            .resourceSecondType(UPDATED_RESOURCE_SECOND_TYPE)
            .resourceFirstPoolFlat(UPDATED_RESOURCE_FIRST_POOL_FLAT)
            .resourceFirstPoolPerLevel(UPDATED_RESOURCE_FIRST_POOL_PER_LEVEL)
            .resourceFirstRegenerationFlat(UPDATED_RESOURCE_FIRST_REGENERATION_FLAT)
            .resourceFirstRegenerationPerLevel(UPDATED_RESOURCE_FIRST_REGENERATION_PER_LEVEL)
            .resourceSecondPoolFlat(UPDATED_RESOURCE_SECOND_POOL_FLAT)
            .resourceSecondPoolPerLevel(UPDATED_RESOURCE_SECOND_POOL_PER_LEVEL)
            .resourceSecondRegenerationFlat(UPDATED_RESOURCE_SECOND_REGENERATION_FLAT)
            .resourceSecondRegenerationPerLevel(UPDATED_RESOURCE_SECOND_REGENERATION_PER_LEVEL);

        restChampionMockMvc.perform(put("/api/champions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedChampion)))
            .andExpect(status().isOk());

        // Validate the Champion in the database
        List<Champion> championList = championRepository.findAll();
        assertThat(championList).hasSize(databaseSizeBeforeUpdate);
        Champion testChampion = championList.get(championList.size() - 1);
        assertThat(testChampion.getInternalVersion()).isEqualTo(UPDATED_INTERNAL_VERSION);
        assertThat(testChampion.getLastModified()).isEqualTo(UPDATED_LAST_MODIFIED);
        assertThat(testChampion.getRiotId()).isEqualTo(UPDATED_RIOT_ID);
        assertThat(testChampion.getRiotKey()).isEqualTo(UPDATED_RIOT_KEY);
        assertThat(testChampion.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testChampion.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testChampion.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testChampion.getBlurb()).isEqualTo(UPDATED_BLURB);
        assertThat(testChampion.getAttack()).isEqualTo(UPDATED_ATTACK);
        assertThat(testChampion.getDefence()).isEqualTo(UPDATED_DEFENCE);
        assertThat(testChampion.getMagic()).isEqualTo(UPDATED_MAGIC);
        assertThat(testChampion.getDifficulty()).isEqualTo(UPDATED_DIFFICULTY);
        assertThat(testChampion.getMovementSpeed()).isEqualTo(UPDATED_MOVEMENT_SPEED);
        assertThat(testChampion.getAttackRange()).isEqualTo(UPDATED_ATTACK_RANGE);
        assertThat(testChampion.getArmorFlat()).isEqualTo(UPDATED_ARMOR_FLAT);
        assertThat(testChampion.getArmorPerLevel()).isEqualTo(UPDATED_ARMOR_PER_LEVEL);
        assertThat(testChampion.getSpellBlockFlat()).isEqualTo(UPDATED_SPELL_BLOCK_FLAT);
        assertThat(testChampion.getSpellBlockPerLevel()).isEqualTo(UPDATED_SPELL_BLOCK_PER_LEVEL);
        assertThat(testChampion.getCriticalFlat()).isEqualTo(UPDATED_CRITICAL_FLAT);
        assertThat(testChampion.getCriticalPerLevel()).isEqualTo(UPDATED_CRITICAL_PER_LEVEL);
        assertThat(testChampion.getAttackDamageFlat()).isEqualTo(UPDATED_ATTACK_DAMAGE_FLAT);
        assertThat(testChampion.getAttackDamagePerLevel()).isEqualTo(UPDATED_ATTACK_DAMAGE_PER_LEVEL);
        assertThat(testChampion.getAttackSpeedFlat()).isEqualTo(UPDATED_ATTACK_SPEED_FLAT);
        assertThat(testChampion.getAttackSpeedPerLevel()).isEqualTo(UPDATED_ATTACK_SPEED_PER_LEVEL);
        assertThat(testChampion.getResourceFirstType()).isEqualTo(UPDATED_RESOURCE_FIRST_TYPE);
        assertThat(testChampion.getResourceSecondType()).isEqualTo(UPDATED_RESOURCE_SECOND_TYPE);
        assertThat(testChampion.getResourceFirstPoolFlat()).isEqualTo(UPDATED_RESOURCE_FIRST_POOL_FLAT);
        assertThat(testChampion.getResourceFirstPoolPerLevel()).isEqualTo(UPDATED_RESOURCE_FIRST_POOL_PER_LEVEL);
        assertThat(testChampion.getResourceFirstRegenerationFlat()).isEqualTo(UPDATED_RESOURCE_FIRST_REGENERATION_FLAT);
        assertThat(testChampion.getResourceFirstRegenerationPerLevel()).isEqualTo(UPDATED_RESOURCE_FIRST_REGENERATION_PER_LEVEL);
        assertThat(testChampion.getResourceSecondPoolFlat()).isEqualTo(UPDATED_RESOURCE_SECOND_POOL_FLAT);
        assertThat(testChampion.getResourceSecondPoolPerLevel()).isEqualTo(UPDATED_RESOURCE_SECOND_POOL_PER_LEVEL);
        assertThat(testChampion.getResourceSecondRegenerationFlat()).isEqualTo(UPDATED_RESOURCE_SECOND_REGENERATION_FLAT);
        assertThat(testChampion.getResourceSecondRegenerationPerLevel()).isEqualTo(UPDATED_RESOURCE_SECOND_REGENERATION_PER_LEVEL);
    }

    @Test
    @Transactional
    public void updateNonExistingChampion() throws Exception {
        int databaseSizeBeforeUpdate = championRepository.findAll().size();

        // Create the Champion

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restChampionMockMvc.perform(put("/api/champions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(champion)))
            .andExpect(status().isBadRequest());

        // Validate the Champion in the database
        List<Champion> championList = championRepository.findAll();
        assertThat(championList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteChampion() throws Exception {
        // Initialize the database
        championService.save(champion);

        int databaseSizeBeforeDelete = championRepository.findAll().size();

        // Get the champion
        restChampionMockMvc.perform(delete("/api/champions/{id}", champion.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Champion> championList = championRepository.findAll();
        assertThat(championList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Champion.class);
        Champion champion1 = new Champion();
        champion1.setId(1L);
        Champion champion2 = new Champion();
        champion2.setId(champion1.getId());
        assertThat(champion1).isEqualTo(champion2);
        champion2.setId(2L);
        assertThat(champion1).isNotEqualTo(champion2);
        champion1.setId(null);
        assertThat(champion1).isNotEqualTo(champion2);
    }
}
