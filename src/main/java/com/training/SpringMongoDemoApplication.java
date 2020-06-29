package com.training;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringMongoDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringMongoDemoApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		/*final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Converter<String, Date> strToDt = new Converter<String, Date>() {
			@Override
			public Date convert(MappingContext<String, Date> context) {
				if(context.getSource() == null)
					return null;

				Date date = null;
				try {
					date = sdf.parse(context.getSource());
				} catch (ParseException e) { }
				return date;
			}
		};

		Converter<Date, String> dtToStr = new Converter<Date, String>() {
			@Override
			public String convert(MappingContext<Date, String> context) {
				if(context.getSource() == null)
					return null;

				return sdf.format(context.getSource());
			}
		};

		PropertyMap<VolunteerDto, Volunteer> volunteerMap = new PropertyMap<VolunteerDto, Volunteer>() {
			@Override
			protected void configure() {
				map().setTokenId(source.getTokenId());
			}
		};
		
		modelMapper.addConverter(strToDt);
		modelMapper.addConverter(dtToStr);*/
//		modelMapper.addMappings(volunteerMap);
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return modelMapper;
	}
}
