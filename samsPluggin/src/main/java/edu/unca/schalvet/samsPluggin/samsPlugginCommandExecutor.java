package edu.unca.schalvet.samsPluggin;

/*
    This file is part of samsPluggin

    Foobar is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Foobar is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 */

import java.text.MessageFormat;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.google.common.base.Joiner;

public class samsPlugginCommandExecutor implements CommandExecutor {

    private samsPluggin plugin;

    public samsPlugginCommandExecutor(samsPluggin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        plugin.getLogger().info("onCommand Reached in samsPluggin");

        if (args.length == 0) {
			return false;
		}  else if (args[0].equalsIgnoreCase("iWanaDiamond")) {
			Player randomGuy = (Player) sender;
			Location loc = randomGuy.getLocation();
			World w = loc.getWorld();
			loc.setX(loc.getX() + 3);
			loc.setZ(loc.getZ() + 2);
			Block b = w.getBlockAt(loc);
			b.setTypeId(57);
			plugin.log.info("gave player a diamond"); 
			return true;
		
			}     else if (args[0].equalsIgnoreCase("enchante")) {
			Player randomGuy = (Player) sender;
			Location loc = randomGuy.getLocation();
			World w = loc.getWorld();
			
			if(randomGuy.getInventory().getItemInHand().getTypeId() == 276){
				randomGuy.getInventory().getItemInHand().addEnchantment(Enchantment.DAMAGE_UNDEAD, 5);
				randomGuy.sendMessage("Your Sword Will Now Through Zombies Like Butter!!");
				plugin.log.info("gave player enchantement vs undead lvl 5"); 
			}
			
			else {
				randomGuy.sendMessage("Get your self a diamond sword with IRINVINCIBLE");
				plugin.log.info("player has no sword"); 
			}
			
			return true;
		

			} else if (args[0].equalsIgnoreCase("IRINVINCIBLE")) {
			
			Player randomGuy = (Player) sender;
			Location loc = randomGuy.getLocation();
			World w = loc.getWorld();
			
			
			randomGuy.getInventory().clear();
			randomGuy.getInventory().setHelmet(new ItemStack(Material.DIAMOND_HELMET, 1));
			randomGuy.getInventory().setBoots(new ItemStack(Material.DIAMOND_BOOTS, 1));
			randomGuy.getInventory().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE, 1));
			randomGuy.getInventory().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS, 1));
			randomGuy.getInventory().setItemInHand(new ItemStack(Material.DIAMOND_SWORD, 1));
			randomGuy.sendMessage("Fight Well Brave Warrior");
			plugin.log.info("gave player Diomond armor set and sword"); 
			
			//BELLOW IS OLD CODE THAT DIDNT WORK AS WANTED, BUT WORTH KEEPING.
			
			/*	The idea is that with these loops we fill the space all around the player with TNT:
			 * 	____________________________
			 *  |_(-1,1)__|__(0,1)_|_(1,1)__|
			 *  |_(-1,0)__|_Player_|_(1,0)__|
			 *  |_(-1,-1)_|_(0,-1)_|_(1,-1)_|
			 *  
			 *  The Coordinance above are all created by these loops starting with the bottom left corner
			 *  and each time a block is set a lava block is also created at the same spot only at loc Z-1
			 */
			/*for(int i=1; i<4; i++)
			{
				for(int n=1; n<4; n++)
				{
					if(i-2!=0 && n-2!=0)				//we dont want to place a TNT block on the player himself
					{				
						loc = randomGuy.getLocation();	//resets the loc coordinance to be centered on the player in preperation for next block
						loc.setX(loc.getY() + i-2);		//sets the Y loc starting with bottom (-1)
						loc.setY(loc.getX() + n-2);		//sets the X loc starting with left (-1)
						Block tnt = w.getBlockAt(loc);	//gets the address of the TNT block at players ground level
						loc.setZ(loc.getZ() - 1);		//sets the Y loc to -1 relative to the player
						Block lava = w.getBlockAt(loc);	//gets the address of the lava block, which is one block lover than the TNT block
						tnt.setTypeId(46);				//gives the TNT block the proper TNT ID
						lava.setTypeId(11);				//gives the lava block the proper lava ID
						plugin.getLogger().info("inside loop: i="+i+", n="+n); //shows whats going on in the terminal
						
					}
				}
			}*/
			
			return true;
			
		} else if (args[0].equalsIgnoreCase("secret") && sender.hasPermission("pluggin.secret")) {
			Player randomGuy = (Player) sender;
			Location loc = randomGuy.getLocation();
			World w = loc.getWorld();
			randomGuy.sendMessage("To be or not to be THAT is the question");
			plugin.log.info("Sent message to player"); 
			return true;
		}
		/*else if (args[0].equalsIgnoreCase("rain") && sender.hasPermission("pluggin.rain")) {
			Player fred =(Player) sender;
			Location loc = fred.getBedSpawnLocation();
			fred.teleport(loc);
			fred.sendMessage("Sleepy time");
			return true;
		}*/else {
			return false;
		}
    }
    
    //Old pluggin
    /*commands:			
    	   kaboom:
    	     description: creates a wall of TNT block set atop lava around the player
    	   iWanaDiamond:
    	   	  description: creates a block of diamond 
    	   secret:
    	      description: prints a message*/
    //public void kaboom(){}
}
