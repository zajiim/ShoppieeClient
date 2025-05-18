package com.example.shoppieeclient.presentation.navigation.main

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.example.shoppieeclient.presentation.home.accounts.AccountsScreen
import com.example.shoppieeclient.presentation.home.address.AddressScreen
import com.example.shoppieeclient.presentation.home.home.HomeScreen
import com.example.shoppieeclient.presentation.home.cart.CartScreen
import com.example.shoppieeclient.presentation.home.delete_account.DeleteAccountScreen
import com.example.shoppieeclient.presentation.home.details.DetailsScreen
import com.example.shoppieeclient.presentation.home.details.DetailsViewModel
import com.example.shoppieeclient.presentation.home.favorites.FavoriesScreen
import com.example.shoppieeclient.presentation.home.notifications.NotificationsScreen
import com.example.shoppieeclient.presentation.home.payment.PaymentScreen
import com.example.shoppieeclient.presentation.home.profile.ProfileScreen
import com.example.shoppieeclient.presentation.home.checkout.CheckOutScreen
import com.example.shoppieeclient.presentation.home.checkout.CheckOutViewModel
import com.example.shoppieeclient.presentation.home.order.OrderScreen
import com.example.shoppieeclient.presentation.navigation.Destination
import com.example.shoppieeclient.presentation.navigation.graphs.Graphs
import org.koin.androidx.compose.koinViewModel


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
fun NavGraphBuilder.homeNavGraph(
    navController: NavHostController,
    sharedTransitionScope: SharedTransitionScope,
    checkOutViewModel: CheckOutViewModel
) {
    navigation<Graphs.Home>(
        startDestination = Destination.Home
    ) {
        composable<Destination.Home> {
            HomeScreen(
                onNavigateToDetails = {
                    navController.navigate(
                        Destination.Details(
                            id = it
                        )
                    )
                },
                sharedTransitionScope = sharedTransitionScope,
                animatedVisibilityScope = this
            )
        }

        composable<Destination.Details> { backStackEntry ->
            val user = backStackEntry.toRoute<Destination.Details>()
            val viewModel: DetailsViewModel = koinViewModel()
            DetailsScreen(
                viewModel = viewModel,
                onNavigateClick = { navController.navigateUp() },
                sharedTransitionScope = sharedTransitionScope,
                animatedVisibilityScope = this
            )
        }

        composable<Destination.Favorites> {
            FavoriesScreen()
        }
        composable<Destination.Cart> {
            CartScreen(
                onNavigateClick = {
                    navController.navigateUp()
                },
                onCheckOutClicked = {
                    navController.navigate(Destination.CheckOut)
                }
            )
        }
        composable<Destination.Notifications> {
            NotificationsScreen()
        }
        composable<Destination.Profile> {
            ProfileScreen(
                onNavigateClick = {
                    navController.navigateUp()
                },
                onProfileSettingsClick = {
                    navController.navigate(Destination.Account)
                },
                onShippingAddressClick = {
                    navController.navigate(Destination.CheckOut)
                },
                onPaymentInfoClick = {
                    navController.navigate(Destination.Payment)

                },
                onDeleteAccountClick = {
                    navController.navigate(Destination.Delete)
                },
                onOrderClick = {
                    navController.navigate(Destination.Order)
                }
            )
        }


        composable<Destination.Account> {
            AccountsScreen(
                onNavigateClick = {
                    navController.navigateUp()
                }
            )
        }

        composable<Destination.CheckOut> {
            CheckOutScreen(
                viewModel = checkOutViewModel,
                onNavigateClick = {
                    navController.navigateUp()
                },
                onAddressRoute = {
                    navController.navigate(Destination.Address)
                },
                onPaymentRoute = {
                    navController.navigate(Destination.Payment)
                },
                onPaymentSuccess = {
                    navController.navigateUp()
                }
            )
        }



        composable<Destination.Payment> {
            PaymentScreen(
                onNavigateClick = {
                    navController.navigateUp()
                }
            )
        }

        composable<Destination.Address> {
            AddressScreen(
                onNavigateClick = {
                    navController.navigateUp()
                }
            )
        }

        composable<Destination.Delete> {
            DeleteAccountScreen(
                onNavigateClick = {
                    navController.navigateUp()
                }
            )
        }
        composable<Destination.Order> {
            OrderScreen(
                onNavigateClick = {
                    navController.navigateUp()
                }
            )
        }

    }
}
