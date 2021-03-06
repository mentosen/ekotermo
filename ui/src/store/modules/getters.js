const getters = {
  sidebar: state => state.app.sidebar,
  language: state => state.app.language,
  size: state => state.app.size,
  isMobile: state => state.app.isMobile,
  isPartner: state => state.app.isPartner,
  fromApplication: state => state.app.application,
  visitedViews: state => state.tagsView.visitedViews,
  cachedViews: state => state.tagsView.cachedViews,
  token: state => state.user.token,
  avatar: state => state.user.avatar,
  name: state => state.user.name,
  userInfo: state => state.user.userInfo,
  isAuth: state => state.user.userInfo.isAuth,
  introduction: state => state.user.introduction,
  status: state => state.user.status,
  roles: state => state.user.roles,
  setting: state => state.user.setting,
  permission_routes: state => state.permission.routes,
  addRoutes: state => state.permission.addRoutes,
  errorLogs: state => state.errorLog.logs,
  event: state => state.event.event,
  wsConnect: state => state.event.wsConnect,
  eventId: state => state.event.eventId,
  integrationEventId: state => state.event.integrationId,
  ticketType: state => state.ticketType.types,
  fieldsProfile: state => state.fieldsProfile.fields,
  order: state => state.order.order
}
export default getters
