db.ticketInfo.ensureIndex({'ticket_no':1,'coupon_info.coupon_no':1});
db.ticketInfo.ensureIndex({'coupon_info.coupon_no':1});
db.ticketInfo.ensureIndex({'status':1,'coupon_info.coupon_no':1});