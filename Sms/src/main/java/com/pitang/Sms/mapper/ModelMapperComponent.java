package com.pitang.Sms.mapper;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;

import com.pitang.Sms.dto.ProfileDto;
import com.pitang.Sms.dto.UserDto;
import com.pitang.Sms.model.ProfileModel;
import com.pitang.Sms.model.UserModel;

public class ModelMapperComponent {

	public static final ModelMapper modelMapper = new ModelMapper();
	
	private void configurer() {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		modelMapper.addMappings(new PropertyMap<UserModel, UserDto>(){

			@Override
			protected void configure() {
				
				//UserModel to UserDto
				map().setId(source.getId());
				map().setUserName(source.getUserName());
				map().setFirstName(source.getFirstName());
				map().setLastName(source.getLastName());
				map().setEmail(source.getEmail());
				map().setPassword(null);
				when(Conditions.isNotNull()).using(ModelConverter.convertStatusToString).map(source.isStatus()).setStatus(null);
				
				//ProfileModel to ProfileDto
				map().getProfileDto().setId(source.getProfileModel().getId());
				map().getProfileDto().setPhoneNumber(source.getProfileModel().getPhoneNumber());
				using(ModelConverter.convertDatetoString).map(source.getProfileModel().getDateOfBirth()).getProfileDto().setDateOfBirth(null);
				map().getProfileDto().setAdress(source.getProfileModel().getAdress());
				map().getProfileDto().setCountry(source.getProfileModel().getCountry());
				map().getProfileDto().setState(source.getProfileModel().getState());
				map().getProfileDto().setCity(source.getProfileModel().getCity());
				map().getProfileDto().setStreet(source.getProfileModel().getStreet());
				map().getProfileDto().setZipCode(source.getProfileModel().getZipCode());
				
			}
			
		});
		
		modelMapper.addMappings(new PropertyMap<UserDto, UserModel>(){

			@Override
			protected void configure() {
				
				//UserDto to UserModel
				map().setId(source.getId());
				map().setUserName(source.getUserName());
				map().setFirstName(source.getFirstName());
				map().setLastName(source.getLastName());
				map().setEmail(source.getEmail());
				map().setPassword(source.getPassword());
				when(Conditions.isNotNull()).using(ModelConverter.convertStatusToBoolean).map(source.getStatus()).setStatus(false);
				skip().setProfileModel(null);
				
			}
			
		});
		
		modelMapper.addMappings(new PropertyMap<ProfileModel, ProfileDto>(){

			@Override
			protected void configure() {
				
				//ProfileModel to ProfileDto
				map().setId(source.getId());
				map().setPhoneNumber(source.getPhoneNumber());
				using(ModelConverter.convertDatetoString).map(source.getDateOfBirth()).setDateOfBirth(null);
				map().setAdress(source.getAdress());
				map().setCountry(source.getCountry());
				map().setState(source.getState());
				map().setCity(source.getCity());
				map().setStreet(source.getStreet());
				map().setZipCode(source.getZipCode());
				
			}
			
		});
		
		modelMapper.addMappings(new PropertyMap<ProfileDto, ProfileModel>(){

			@Override
			protected void configure() {
				
				//ProfileDto to ProfileModel
				map().setId(source.getId());
				map().setPhoneNumber(source.getPhoneNumber());
				using(ModelConverter.convertDatetoString).map(source.getDateOfBirth()).setDateOfBirth(null);
				map().setAdress(source.getAdress());
				map().setCountry(source.getCountry());
				map().setState(source.getState());
				map().setCity(source.getCity());
				map().setStreet(source.getStreet());
				map().setZipCode(source.getZipCode());
				skip().setUserModel(null);
				
			}
			
		});
		
	}
	
}
