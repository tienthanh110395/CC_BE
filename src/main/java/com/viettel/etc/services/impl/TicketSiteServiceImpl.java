package com.viettel.etc.services.impl;

import com.viettel.etc.dto.ActionAuditDTO;
import com.viettel.etc.dto.TicketSiteConfigDTO;
import com.viettel.etc.dto.TicketSiteSearchDTO;
import com.viettel.etc.dto.TicketSiteUserDTO;
import com.viettel.etc.repositories.TicketSiteRepository;
import com.viettel.etc.repositories.tables.TicketSiteRepositoryJPA;
import com.viettel.etc.repositories.tables.TicketSiteUserRepositoryJPA;
import com.viettel.etc.repositories.tables.entities.ActionAuditDetailEntity;
import com.viettel.etc.repositories.tables.entities.ActionAuditEntity;
import com.viettel.etc.repositories.tables.entities.TicketSiteEntity;
import com.viettel.etc.repositories.tables.entities.TicketSiteUserEntity;
import com.viettel.etc.services.ActionAuditService;
import com.viettel.etc.services.TicketSiteService;
import com.viettel.etc.services.tables.ActionAuditServiceJPA;
import com.viettel.etc.services.tables.TicketSiteConfigServiceJPA;
import com.viettel.etc.services.tables.TicketSiteServiceJPA;
import com.viettel.etc.services.tables.TicketSiteUserServiceJPA;
import com.viettel.etc.utils.AppProperties;
import com.viettel.etc.utils.FnCommon;
import com.viettel.etc.xlibrary.core.entities.ResultSelectEntity;
import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TicketSiteServiceImpl implements TicketSiteService {
    private static final Logger log = Logger.getLogger(TicketSiteServiceImpl.class);
    private static final org.slf4j.Logger LOGNEW = LoggerFactory.getLogger(TicketSiteServiceImpl.class);

    @Autowired
    TicketSiteRepository ticketSiteRepository;

    @Autowired
    TicketSiteConfigServiceJPA ticketSiteConfigServiceJPA;

    @Autowired
    TicketSiteRepositoryJPA ticketSiteRepositoryJPA;

    @Autowired
    TicketSiteUserRepositoryJPA ticketSiteUserRepositoryJPA;

    @Autowired
    TicketSiteServiceJPA ticketSiteServiceJPA;

    @Autowired
    ActionAuditService actionAuditService;

    @Autowired
    ActionAuditServiceJPA actionAuditServiceJPA;

    @Autowired
    AppProperties appProperties;

    @Autowired
    TicketSiteUserServiceJPA ticketSiteUserServiceJPA;

    @Override
    public Object searchTicketSite(Authentication authentication, TicketSiteSearchDTO dto) {
        log.info("TicketSiteServiceImpl start searchTicketSite ");
        return ticketSiteRepository.searchTicketSite(authentication, dto);
    }

    @Override
    @Transactional
    public Object onDelete(Long ticketSiteId) {
        log.info("TicketSiteServiceImpl start onDelete ");
        try {
            TicketSiteEntity ticketSite = ticketSiteRepositoryJPA.getOne(ticketSiteId);
            if (ticketSite.getSiteId() != null) {
                ticketSiteServiceJPA.doDelete(ticketSiteId);

                /* Luu log */
                String actionType = ActionAuditDetailEntity.ActionName.DELETE.value;
                ActionAuditEntity actionAuditEntity = new ActionAuditEntity();
                actionAuditEntity.setTicketStatus(ticketSite.getStatus());
                actionAuditEntity.setCreateDate(ticketSite.getCreateDate());
                actionAuditEntity.setTicketAssignId(ticketSite.getSiteId());
                actionAuditEntity.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.TICKET_SITE.value);
                actionAuditServiceJPA.save(actionAuditEntity);
                actionAuditService.saveActAuditDetail(actionAuditEntity.getActionAuditId(), ticketSite, null, ticketSiteId, actionType);
                return true;
            }
        } catch (Exception ex) {
            log.error("Has error TicketSiteServiceImpl onDelete", ex);
        }
        return false;
    }

    @Override
    @Transactional
    public Object changeStatus(TicketSiteConfigDTO dto, Authentication authentication) {
        log.info("TicketSiteServiceImpl start changeStatus ");
        try {
            TicketSiteEntity ticketSite = ticketSiteRepositoryJPA.findById(dto.getTicketSiteId()).get();
            TicketSiteEntity oldEntity = (TicketSiteEntity) ticketSite.clone();
            ticketSite.setUpdateUser(FnCommon.getUserLogin(authentication));
            ticketSite.setUpdateDate(new Date(System.currentTimeMillis()));
            if (TicketSiteEntity.Status.INVALID.value.equals(ticketSite.getStatus())) {
                ticketSite.setStatus(TicketSiteEntity.Status.VALID.value);
            } else {
                ticketSite.setStatus(TicketSiteEntity.Status.INVALID.value);
            }
            ticketSiteConfigServiceJPA.save(ticketSite);

            /* Luu log */
            String actionType = ActionAuditDetailEntity.ActionName.UPDATE.value;

            ActionAuditEntity actionAuditEntity = new ActionAuditEntity();
            actionAuditEntity.setDescription(oldEntity.getSiteName());
            actionAuditEntity.setStatus(ticketSite.getStatus());
            actionAuditEntity.setCreateDate(ticketSite.getCreateDate());
            actionAuditEntity.setIpPc("localhost");
            actionAuditEntity.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.TICKET_SITE.value);
            ActionAuditEntity actionAuditEntitySave = actionAuditServiceJPA.save(actionAuditEntity);
            actionAuditService.saveActAuditDetail(actionAuditEntitySave.getActionAuditId(), oldEntity, ticketSite, ticketSite.getSiteId(), actionType);

            return true;
        } catch (Exception ex) {
            log.info("Has error TicketSiteServiceImpl changeStatus", ex);
        }
        return false;
    }

    @Override
    @Transactional
    public Object updateStatusMultiple(TicketSiteConfigDTO dto, Authentication authentication) {
        log.info("TicketSiteServiceImpl start updateStatusMultiple ");
        String actionType = ActionAuditDetailEntity.ActionName.UPDATE.value;
        try {
            if (!dto.getLstIdsActive().isEmpty()) {
                List<TicketSiteEntity> ticketTypes = ticketSiteServiceJPA.findAllById(dto.getLstIdsActive());
                for (TicketSiteEntity ticketType : ticketTypes) {
                    TicketSiteEntity oldEntity = (TicketSiteEntity) ticketType.clone();
                    ticketType.setStatus(TicketSiteEntity.Status.INVALID.value);
                    ticketType.setUpdateUser(FnCommon.getUserLogin(authentication));
                    ticketType.setUpdateDate(new Date(System.currentTimeMillis()));
                    ticketSiteServiceJPA.save(ticketType);
                    ActionAuditEntity actionAuditEntity = new ActionAuditEntity();
                    actionAuditEntity.setStatus(ticketType.getStatus());
                    actionAuditEntity.setDescription(oldEntity.getSiteName());
                    actionAuditEntity.setCreateDate(ticketType.getCreateDate());
                    actionAuditEntity.setActionUserName(FnCommon.getUserLogin(authentication));
                    actionAuditEntity.setIpPc("localhost");
                    actionAuditEntity.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.TICKET_SITE.value);
                    ActionAuditEntity actionAuditEntitySave = actionAuditServiceJPA.save(actionAuditEntity);
                    actionAuditService.saveActAuditDetail(actionAuditEntitySave.getActionAuditId(), oldEntity, ticketType, ticketType.getSiteId(), actionType);
                }
            }
            if (!dto.getLstIdsInactive().isEmpty()) {
                List<TicketSiteEntity> ticketTypes = ticketSiteServiceJPA.findAllById(dto.getLstIdsInactive());
                for (TicketSiteEntity ticketType : ticketTypes) {
                    TicketSiteEntity oldEntity = (TicketSiteEntity) ticketType.clone();
                    ticketType.setStatus(TicketSiteEntity.Status.VALID.value);
                    ticketType.setUpdateUser(FnCommon.getUserLogin(authentication));
                    ticketType.setUpdateDate(new Date(System.currentTimeMillis()));
                    ticketSiteServiceJPA.save(ticketType);
                    ActionAuditEntity actionAuditEntity = new ActionAuditEntity();
                    actionAuditEntity.setDescription(oldEntity.getSiteName());
                    actionAuditEntity.setStatus(ticketType.getStatus());
                    actionAuditEntity.setCreateDate(ticketType.getCreateDate());
                    actionAuditEntity.setActionUserName(FnCommon.getUserLogin(authentication));
                    actionAuditEntity.setIpPc("localhost");
                    actionAuditEntity.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.TICKET_SITE.value);
                    ActionAuditEntity actionAuditEntitySave = actionAuditServiceJPA.save(actionAuditEntity);
                    actionAuditService.saveActAuditDetail(actionAuditEntitySave.getActionAuditId(), oldEntity, ticketType, ticketType.getSiteId(), actionType);
                }
            }
            return true;
        } catch (Exception ex) {
            log.error("Has error TicketSiteServiceImpl updateStatusMultiple", ex);
        }
        return false;
    }

    @Override
    public Object getTicketSite(Authentication authentication) {
        log.info("TicketSiteServiceImpl start getTicketSite ");
        return ticketSiteRepository.getTicketSite(authentication);
    }

    @Override
    public Object createUpdate(Authentication authentication, TicketSiteConfigDTO dto, TicketSiteUserDTO dtoSiteUser) {
        log.info("TicketSiteServiceImpl start createOrUpdate");
        try {
            TicketSiteEntity entity = new TicketSiteEntity();
            String actionType = ActionAuditDetailEntity.ActionName.INSERT.value;
            TicketSiteEntity oldTicketSiteEntity = null;
            if (dto.getTicketSiteId() == null) {
                TicketSiteEntity ticketSite = ticketSiteConfigServiceJPA.findBySiteCode(dto.getTicketSiteCode());
                if (ticketSite != null && ticketSite.getSiteId() != null) {
                    return 1L;
                }
                entity.setCreateUser(FnCommon.getUserLogin(authentication));
                entity.setCreateDate(new Date(System.currentTimeMillis()));
                entity.setUpdateUser(FnCommon.getUserLogin(authentication));
                entity.setUpdateDate(new Date(System.currentTimeMillis()));
                entity.setLevelSite(TicketSiteEntity.Level.ONE.value);
                entity.setSiteCode(" ");
            }
            if (dto.getTicketSiteId() != null) {
                TicketSiteEntity siteEntity = ticketSiteConfigServiceJPA.findBySiteCodeAndSiteIdNot(dto.getTicketSiteCode(), dto.getTicketSiteId());
                if (siteEntity != null) {
                    return 1L;
                }
                Optional<TicketSiteEntity> optional = ticketSiteRepositoryJPA.findById(dto.getTicketSiteId());
                if (optional.isPresent()) {
                    entity = optional.get();
                    oldTicketSiteEntity = (TicketSiteEntity) entity.clone();
                    actionType = ActionAuditDetailEntity.ActionName.UPDATE.value;
                    entity.setUpdateUser(FnCommon.getUserLogin(authentication));
                    entity.setUpdateDate(new Date(System.currentTimeMillis()));
                }
            }
            entity.setLevelSite(dto.getTicketSiteLv());
            entity.setSiteName(dto.getTicketSiteName());
            entity.setEmail(dto.getTicketSiteEmail());
            entity.setPhone(dto.getTicketSitePhoneNumber());
            entity.setStatus(dto.getStatus());

            TicketSiteEntity entitylog = ticketSiteRepositoryJPA.save(entity);
            entitylog.setSiteCode(entitylog.getSiteId().toString());
            ticketSiteServiceJPA.saveAndFlush(entitylog);

            //Save User
            if (dto.getLstUserHandle().size() > 0) {
                List<TicketSiteUserEntity> createLst = dto.getLstUserHandle().stream().filter(u -> u.getSiteId() == null).map(u -> {
                    TicketSiteUserEntity userEntity = new TicketSiteUserEntity();
                    userEntity.setSiteId(entitylog.getSiteId());
                    userEntity.setCreateUser(entitylog.getCreateUser());
                    userEntity.setCreateDate(entitylog.getCreateDate());
                    userEntity.setStatus(entitylog.getStatus());
                    userEntity.setUserName(u.getUserName());
                    userEntity.setStaffName(u.getName());
                    userEntity.setPhone(u.getPhone());
                    userEntity.setEmail(u.getMail());
                    ticketSiteUserRepositoryJPA.save(userEntity);
                    return userEntity;
                }).collect(Collectors.toList());
                if (createLst.size() > 0) {
                    String actionTypeUser = ActionAuditDetailEntity.ActionName.INSERT.value;
                    TicketSiteUserEntity oldEntityUser = null;
                    List<TicketSiteUserEntity> entityLogUsers = ticketSiteUserServiceJPA.saveAll(createLst);
                    for (TicketSiteUserEntity entityLogUser : entityLogUsers) {
                        ActionAuditDTO actionAuditDTO = new ActionAuditDTO();
                        actionAuditDTO.setDescription(entitylog.getSiteName());
                        actionAuditDTO.setTicketStatus(entityLogUser.getStatus());
                        actionAuditDTO.setCreateDate(entityLogUser.getCreateDate());
                        actionAuditDTO.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.TICKET_SITE.value);
                        ActionAuditEntity actionAuditEntity = actionAuditService.saveActAudit(authentication, actionAuditDTO);
                        actionAuditService.saveActAuditDetail(actionAuditEntity.getActionAuditId(), oldEntityUser, entityLogUser, entityLogUser.getTicketSiteUserId(), actionTypeUser);
                    }
                }
            }

            List<TicketSiteUserEntity> oldLst = ticketSiteUserServiceJPA.findBySiteId(entitylog.getSiteId());
            for (TicketSiteUserEntity user : oldLst) {
                Optional<TicketSiteConfigDTO.UserHandle> exist = dto.getLstUserHandle().stream()
                        .filter(x -> x.getUserName().equals(user.getUserName()))
                        .findFirst();

                if (!exist.isPresent()) {
                    ticketSiteUserServiceJPA.deleteById(user.getTicketSiteUserId());
                    String actionTypeUserUpdate = ActionAuditDetailEntity.ActionName.UPDATE.value;
                    ActionAuditDTO actionAuditDTO = new ActionAuditDTO();
                    actionAuditDTO.setDescription(entitylog.getSiteName());
                    actionAuditDTO.setTicketStatus(user.getStatus());
                    actionAuditDTO.setCreateDate(user.getCreateDate());
                    actionAuditDTO.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.TICKET_SITE.value);
                    ActionAuditEntity actionAuditEntity = actionAuditService.saveActAudit(authentication, actionAuditDTO);
                    actionAuditService.saveActAuditDetailDelete(actionAuditEntity.getActionAuditId(), user, exist, user.getTicketSiteUserId(), actionTypeUserUpdate);
                }
            }

            /* Luu log */
            ActionAuditDTO actionAuditDTO = new ActionAuditDTO();
            actionAuditDTO.setDescription(entitylog.getSiteName());
            actionAuditDTO.setTicketStatus(entitylog.getStatus());
            actionAuditDTO.setCreateDate(new Date(System.currentTimeMillis()));
            actionAuditDTO.setActionUserName(entitylog.getCreateUser());
            actionAuditDTO.setActTypeId(FnCommon.LOG_CAU_HINH_DANH_MUC.TICKET_SITE.value);
            ActionAuditEntity actionAuditEntity = actionAuditService.saveActAudit(authentication, actionAuditDTO);
            actionAuditService.saveActAuditDetail(actionAuditEntity.getActionAuditId(), oldTicketSiteEntity, entitylog, entitylog.getSiteId(), actionType);

            return true;
        } catch (Exception ex) {
            log.error("Has error TicketSiteServiceImpl createOrUpdate", ex);
        }
        return false;
    }

    @Override
    public Object getDataDetail(Long ticketSiteId, TicketSiteConfigDTO dto) {
        log.info("TicketSiteServiceImpl start getDataDetail ");
        TicketSiteConfigDTO ticketSite = (TicketSiteConfigDTO) ticketSiteRepository.getDataDetail(ticketSiteId, dto);
        ResultSelectEntity lstUserHandle = ticketSiteRepository.getLstUserHandle(ticketSiteId, dto);
        ticketSite.setLstUserHandle((List<TicketSiteConfigDTO.UserHandle>) lstUserHandle.getListData());
        return ticketSite;
    }

    @Override
    public Object getAllSiteUser(Authentication authentication) {
        log.info("TicketSiteServiceImpl start getDataDetail ");
        List<String> lstUserNameInDb = ticketSiteUserServiceJPA.findDistinctByUserNameIn();
        return lstUserNameInDb;
    }

    @Override
    public Object getAllUser(Authentication authentication) {
        String urlGetMember = appProperties.ccUserMember.replace("{groupId}", appProperties.keycloakCcGroupId);
        LOGNEW.info("GET USER KEYCLOAK SERVICE: {}",urlGetMember);
        Object data = FnCommon.getMemberForGroup(urlGetMember, appProperties.keycloakLogin, appProperties.keycloakCcClientId, appProperties.keycloakCcAdminUserName, appProperties.keycloakCcAdminUserPass, appProperties.keycloakCcSecret);
        return data;
    }
}

