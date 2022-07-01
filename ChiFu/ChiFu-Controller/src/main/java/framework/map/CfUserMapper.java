package framework.map;

import org.mapstruct.Mapper;

import framework.model.CfUserM;

@Mapper
public interface CfUserMapper {
	/**
	 * 用来调用实例 实际开发中可使用注入Spring 不写
	 */
//    ChiUserMapper CAR_MAPPING = Mappers.getMapper(ChiUserMapper.class);

	/**
	 * 源类型 目标类型 成员变量相同类型 相同变量名 不用写{@link org.mapstruct.Mapping}来映射
	 *
	 * @param ChiUser the ChiUser
	 * @return the ChiUserDto
	 */
	CfUserMDto cfUserMToCfUserMDTO(CfUserM cfUserM);

	CfUserM cfUserDtoToCfUserM(CfUserMDto cfUserMDto);
}
