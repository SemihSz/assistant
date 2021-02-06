package com.spring.assistant.assistant;

import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;

/**
 * @author semih on Mayıs, 2020, 18.05.2020, 18:16:33
 */
@Service
public class BotService extends ListenerAdapter {

	@Value("${jda.discord.api}")
	String token;
	private JDA jda;

	public void startBot() throws LoginException, InterruptedException {
		this.jda = new JDABuilder(AccountType.BOT)
				.setToken(token)
				.addEventListeners(new BotService())
				.build();
	}

	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		final Message msg = event.getMessage();
		if (event.isFromType(ChannelType.TEXT)) {
			System.out.printf("[%s][%s] %#s: %s%n", event.getGuild().getName(),
					event.getChannel().getName(), event.getAuthor(), event.getMessage().getContentDisplay());
		}

		if (msg.getContentRaw().equals("!ping")) {
			final MessageChannel channel = event.getChannel();
			long time = System.currentTimeMillis();
			channel.sendMessage("pong!")
					.queue(response -> {
						response.editMessageFormat("Pong: %d ms", System.currentTimeMillis() - time).queue();
					});
		} else {
			System.out.printf("[PM] %#s: %s%n", event.getAuthor(), event.getMessage().getContentDisplay());
		}
	}


}

