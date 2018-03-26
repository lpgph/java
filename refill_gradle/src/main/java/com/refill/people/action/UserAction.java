package com.refill.people.action;

import com.refill.base.action.BaseAction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Peter Guo
 * @version 0.1
 * @ClassName UserAction
 * @Description 用户控制层
 * @date 17/05/02
 */
@Controller
@RequestMapping("/${requestPath}/people")
public class UserAction extends BaseAction {
}
